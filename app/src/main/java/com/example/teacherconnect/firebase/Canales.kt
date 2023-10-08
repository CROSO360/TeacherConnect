package com.example.teacherconnect.firebase

data class Canales(
    val id: String?=null,
    val nombreCanal: String,
    val usuarioId: String
) {
    fun toMap(): Map<String, Any> {
        val result = mutableMapOf(
            "nombreCanal" to this.nombreCanal,
            "usuarioId" to this.usuarioId
        )
        this.id?.let {
            result["id"] = it
        }
        return result
    }
}

