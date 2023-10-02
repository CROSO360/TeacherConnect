package com.example.teacherconnect.interfaces.login

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.teacherconnect.firebase.Usuarios
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch


class LoginViewModel: ViewModel(){
    private val auth:FirebaseAuth=Firebase.auth
    private val _loading= MutableLiveData(false)

    fun signWithEmailAndPassword(email:String,password:String, home: ()->Unit)
            = viewModelScope.launch {
        try {
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener {  task->
                    if(task.isSuccessful){
                        Log.d("Inicio","Hecho brou")
                        home()
                    }
                    else{
                        Log.d("Inicio","Error: ${task.result.toString()}")
                    }
                }
        }
        catch (ex:Exception){
            Log.d("Inicio","Error: ${ex.message}")

        }
    }
    fun createUserWithEmailAndPassword(email: String, password: String, name:String, occupation:String,home:()->Unit){
        if(_loading.value==false){
            _loading.value=true
            auth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {

                        createUser(email,password,name,occupation)
                        home()
                    } else {
                        Log.d("TeacherConnect", "Error: ${task.result.toString()}")
                    }
                    _loading.value=false
                }
        }
    }

    private fun createUser(email: String,password: String,name: String,occupation: String) {
        val userId=auth.currentUser?.uid

        val user=Usuarios(
            id=userId.toString(),
            email= email,
            password= password,
            name= name,
            occupation= occupation
        ).toMap()
        FirebaseFirestore.getInstance().collection("users")
            .add(user)
            .addOnSuccessListener {
                Log.d("TeacherConnect","Creado ${it.id}")
            }.addOnFailureListener {
                Log.d("TeacherConnect", "Error: ${it}")
            }
    }
}