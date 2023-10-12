package com.example.teacherconnect.firebase

data class Canales(
    val id: String?=null,
    val nombreCanal: String="",
    val profesorId: String="",
    val imagenId:String="",
    val pin:String="",
    val estudiantes: List<String> = listOf()
) {
    fun toMap(): Map<String, Any> {
        val result = mutableMapOf(
            "nombreCanal" to this.nombreCanal,
            "profesorId" to this.profesorId,
            "imagenId" to this.imagenId,
            "pin" to this.pin,
            "estudiantes" to this.estudiantes

        )
        this.id?.let {
            result["id"] = it
        }
        return result
    }
}

