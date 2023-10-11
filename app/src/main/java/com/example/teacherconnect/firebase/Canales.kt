package com.example.teacherconnect.firebase

data class Canales(
    val id: String?=null,
    val nombreCanal: String="",
    val usuarioId: String="",
    val imagenId:String=""
) {
    fun toMap(): Map<String, Any> {
        val result = mutableMapOf(
            "nombreCanal" to this.nombreCanal,
            "usuarioId" to this.usuarioId,
            "imagenId" to this.imagenId
        )
        this.id?.let {
            result["id"] = it
        }
        return result
    }
}

