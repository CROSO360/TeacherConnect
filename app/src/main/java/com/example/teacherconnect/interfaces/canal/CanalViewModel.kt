package com.example.teacherconnect.interfaces.canal

import android.util.Log
import com.example.teacherconnect.firebase.Canales
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import androidx.lifecycle.ViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.LiveData
import com.example.teacherconnect.firebase.Imagenes


class CanalViewModel : ViewModel(){
    private val firestore = FirebaseFirestore.getInstance()

    fun createCanal(canal: Canales, onSuccess: (String) -> Unit, onFailure: (Exception) -> Unit) {
        val canalRef = FirebaseFirestore.getInstance().collection("canales").document()

        canalRef.set(canal.toMap()).addOnSuccessListener {
            canalRef.update("id", canalRef.id).addOnSuccessListener {
                onSuccess(canalRef.id)
            }.addOnFailureListener { exception ->
                onFailure(exception)
            }
        }.addOnFailureListener { exception ->
            onFailure(exception)
        }
    }

    fun addCanalToUser(userId: String, canalId: String) {
        val userRef = FirebaseFirestore.getInstance().collection("users").document(userId)

        userRef.update("canales", FieldValue.arrayUnion(canalId))
            .addOnSuccessListener {
                Log.d("TeacherConnect", "Canal añadido al usuario")
            }
            .addOnFailureListener {
                Log.d("TeacherConnect", "Error añadiendo canal: ${it}")
            }
    }
    val canales = MutableLiveData<List<Canales>>()

    fun CanalesDelUsuario(userId: String) {
        val db = FirebaseFirestore.getInstance()
        db.collection("users").document(userId).get()
            .addOnSuccessListener { document ->
                val canalIds = document["canales"] as? List<String> ?: listOf()
                if (canalIds.isNotEmpty()) {
                    db.collection("canales").whereIn("id", canalIds).get()
                        .addOnSuccessListener { querySnapshot ->
                            val canalesList = querySnapshot.documents.mapNotNull { it.toObject(Canales::class.java) }
                            canales.value = canalesList
                        }
                } else {
                    canales.value = listOf()
                }
            }

    }
    fun obtenerImagenesEmoji(): LiveData<List<Imagenes>> {
        val liveData = MutableLiveData<List<Imagenes>>()
        val db = FirebaseFirestore.getInstance()
        db.collection("imagenes")
            .whereEqualTo("categoria", "emoji")
            .get()
            .addOnSuccessListener { result ->
                val images = result.map { it.toObject(Imagenes::class.java) }
                liveData.value = images
            }
        return liveData
    }

}