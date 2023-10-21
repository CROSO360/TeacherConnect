package com.example.teacherconnect.interfaces.horario

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.teacherconnect.firebase.Actividades
import com.example.teacherconnect.firebase.Horarios
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.concurrent.TimeUnit


class HorarioViewModel: ViewModel() {
    private val _horarioId = MutableLiveData<String>()
    val horarioId: LiveData<String> get() = _horarioId

    fun crearHorario(horario: Horarios, onSuccess: (String) -> Unit, onFailure: (Exception) -> Unit) {
        val db = FirebaseFirestore.getInstance().collection("horarios").document()

        db.set(horario.toMap()).addOnSuccessListener {
            db.update("id", db.id).addOnSuccessListener {
                onSuccess(db.id)
            }.addOnFailureListener { exception ->
                onFailure(exception)
            }
        }.addOnFailureListener { exception ->
            onFailure(exception)
        }
    }
    fun addHorarioToUser(userId: String, idHorario: String) {
        val userRef = FirebaseFirestore.getInstance().collection("users").document(userId)

        userRef.update("horarioId", idHorario)
            .addOnSuccessListener {
                Log.d("TeacherConnect", "Horario añadido al usuario")
            }
            .addOnFailureListener {
                Log.d("TeacherConnect", "Error añadiendo horario: ${it}")
            }
    }
    fun horarioCheck(userId: String, horarioCheck: (Boolean) -> Unit) {
        val userRef = FirebaseFirestore.getInstance().collection("users").document(userId)

        userRef.get().addOnSuccessListener { documentSnapshot ->
            val horarioId = documentSnapshot.getString("horarioId")
            horarioCheck(!horarioId.isNullOrEmpty())
        }.addOnFailureListener {
            Log.d("TeacherConnect", "Error verificando horario: ${it}")
        }
    }
    fun eliminarHorario(userId: String, onSuccess: () -> Unit, onFailure: (Exception) -> Unit) {
        val userRef = FirebaseFirestore.getInstance().collection("users").document(userId)

        userRef.get().addOnSuccessListener { documentSnapshot ->
            val horarioId = documentSnapshot.getString("horarioId") ?: return@addOnSuccessListener

            val horarioRef = FirebaseFirestore.getInstance().collection("horarios").document(horarioId)
            horarioRef.delete().addOnSuccessListener {
                userRef.update("horarioId","").addOnSuccessListener {
                    onSuccess()
                }.addOnFailureListener { exception ->
                    onFailure(exception)
                }
            }.addOnFailureListener { exception ->
                onFailure(exception)
            }
        }.addOnFailureListener { exception ->
            onFailure(exception)
        }
    }

    fun obtenerHorarioId() {
        val userId = FirebaseAuth.getInstance().currentUser?.uid ?: return
        val db = FirebaseFirestore.getInstance().collection("users").document(userId)

        db.get()
            .addOnSuccessListener { document ->
                if (document != null && document.exists()) {
                    val horarioId = document.getString("horarioId") ?: ""
                    _horarioId.value = horarioId
                } else {
                    Log.d("Firestore", "No such document")
                }
            }
            .addOnFailureListener { exception ->
                Log.d("Firestore", "Error getting document: ", exception)
            }
    }

    val horasOcupadas: MutableLiveData<List<String>> = MutableLiveData(listOf())

    fun obtenerHorasOcupadasParaDia(horarioId: String?, dia: String) {
        val db = FirebaseFirestore.getInstance()
        db.collection("actividades")
            .whereEqualTo("horarioId", horarioId)
            .whereEqualTo("dia", dia)
            .get()
            .addOnSuccessListener { documents ->
                val horas = mutableListOf<String>()
                for (doc in documents) {
                    val actividad = doc.toObject(Actividades::class.java)
                    horas.addAll(obtenerRangoHoras(actividad.horaEntrada, actividad.horaSalida))
                }
                horasOcupadas.value = horas
            }
    }

    private fun obtenerRangoHoras(horaInicio: String, horaFin: String): List<String> {
        val format = SimpleDateFormat("HH:mm", Locale.getDefault())
        val start = format.parse(horaInicio)!!.time
        val end = format.parse(horaFin)!!.time

        val horas = mutableListOf<String>()
        var current = start

        while (current < end) {
            horas.add(format.format(Date(current)))
            current += TimeUnit.HOURS.toMillis(1)
        }

        return horas
    }
    fun crearActividad(actividad: Actividades, onSuccess: () -> Unit, onFailure: (Exception) -> Unit) {
        val db = FirebaseFirestore.getInstance()
        val actividadRef = db.collection("actividades")
        val horariosRef = db.collection("horarios")

        actividadRef.add(actividad)
            .addOnSuccessListener { documentReference ->
                val docId = documentReference.id
                actividadRef.document(docId).update("id", docId)
                    .addOnSuccessListener {
                        horariosRef.document(actividad.horarioId)
                            .update("actividades", FieldValue.arrayUnion(docId))
                            .addOnSuccessListener {
                                onSuccess.invoke()
                                obtenerHorasOcupadasParaDia(actividad.horarioId, actividad.dia)
                            }
                            .addOnFailureListener { exception ->
                                onFailure.invoke(exception)
                            }
                    }
                    .addOnFailureListener { exception ->
                        onFailure.invoke(exception)
                    }
            }
            .addOnFailureListener { exception ->
                onFailure.invoke(exception)
            }
    }
    fun isHourSelectionValid(hour: String, selectedHours: List<String>, allHours: List<String>): Boolean {
        // Si aún no hay horas seleccionadas, cualquier hora es válida
        if (selectedHours.isEmpty()) return true

        val index = allHours.indexOf(hour)
        val prevHourIndex = index - 1
        val nextHourIndex = index + 1

        // Comprobar si la hora anterior o la hora siguiente están en las horas seleccionadas
        if ((prevHourIndex >= 0 && allHours[prevHourIndex] in selectedHours) ||
            (nextHourIndex < allHours.size && allHours[nextHourIndex] in selectedHours)) {
            return true
        }

        return false
    }



}
