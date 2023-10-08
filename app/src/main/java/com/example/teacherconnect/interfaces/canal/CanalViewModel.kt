package com.example.teacherconnect.interfaces.canal

import android.util.Log
import com.example.teacherconnect.firebase.Canales
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore

class CanalViewModel {
    private val firestore = FirebaseFirestore.getInstance()

    fun createCanal(canal: Canales, onSuccess: (String) -> Unit, onFailure: (Exception) -> Unit) {
        // Crear una referencia para un nuevo documento (canal) en Firestore
        val canalRef = FirebaseFirestore.getInstance().collection("canales").document()

        // Establecer el canal en Firestore
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

}