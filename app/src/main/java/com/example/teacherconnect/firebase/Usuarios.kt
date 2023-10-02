package com.example.teacherconnect.firebase

data class Usuarios(
                    val id: String?,
                    val email: String,
                    val password: String,
                    val name: String,
                    val occupation: String
){
    fun toMap(): MutableMap<String, Any>{
        return mutableMapOf(
            "email" to this.email,
            "password" to this.password,
            "name" to this.name,
            "occupation" to this.occupation
        )
    }
}
