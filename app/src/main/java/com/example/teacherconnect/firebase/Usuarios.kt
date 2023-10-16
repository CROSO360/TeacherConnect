package com.example.teacherconnect.firebase

data class Usuarios(
                    val id: String?=null,
                    val email: String="",
                    val password: String="",
                    val name: String="",
                    val occupation: String="",
                    val fotoPerfilId:String="",
                    val canales: List<String> = listOf()
){
    fun toMap(): MutableMap<String, Any>{
        val result = mutableMapOf(
            "email" to this.email,
            "password" to this.password,
            "name" to this.name,
            "occupation" to this.occupation,
            "fotoPerfilId" to this.fotoPerfilId,
            "canales" to this.canales
        )
        this.id?.let {
            result["id"] = it
        }

        return result
    }
}
