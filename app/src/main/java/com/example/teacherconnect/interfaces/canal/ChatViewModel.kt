package com.example.teacherconnect.interfaces.canal

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.teacherconnect.firebase.Canales
import com.example.teacherconnect.firebase.Mensajes
import com.example.teacherconnect.firebase.Usuarios
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentId
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.teacherconnect.firebase.Imagenes
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class ChatViewModel : ViewModel() {

    //

    val canalViewModel = CanalViewModel()

    fun obtenerCanalPorId(canaId: String?): LiveData<Canales?> {
        val liveData = MutableLiveData<Canales?>()
        val db = FirebaseFirestore.getInstance()
        canaId?.let {
            db.collection("canales")
                .document(it)
                .get()
                .addOnSuccessListener { snapshot ->
                    val channel = snapshot.toObject(Canales::class.java)
                    liveData.value = channel
                }
        }
        return liveData
    }


    private val _mensajes = MutableLiveData<List<Mensajes>>()

    val mensajes: LiveData<List<Mensajes>> = _mensajes

    fun obtenerMensajesPorCanal(canalId: String?, lastMessageDate: Timestamp? = null) {
        viewModelScope.launch(Dispatchers.IO) {
            val db = FirebaseFirestore.getInstance()
            var query = db.collection("mensajes")
                .whereEqualTo("canalID", canalId)
                .orderBy("fecha", Query.Direction.DESCENDING)

            // Si se proporciona una fecha, carga mensajes anteriores a esa fecha
            lastMessageDate?.let {
                query = query.whereLessThan("fecha", it)
            }

            try {
                val snapshots = query.get().await() // Usando await() para esperar el resultado
                val nuevosMensajes = snapshots.toObjects(Mensajes::class.java)
                val combinedList = nuevosMensajes + (_mensajes.value ?: listOf())
                _mensajes.postValue(combinedList) // Actualizando el MutableLiveData
            } catch (e: Exception) {
                // Manejo de errores si es necesario
                Log.e("MiTag", "Error al obtener mensajes", e)
            }
        }
    }



    fun EnviarMensaje(contenido: String, canalId: String?, userId: String?) {
        val db = FirebaseFirestore.getInstance()

        val nuevoMensaje = Mensajes(
            canalID = canalId ?: "",
            usuarioID = userId ?: "",
            contenido = contenido,
            fecha = Timestamp.now(),
            tipo = "mensaje",
            imagenID = null
        )

        db.collection("mensajes")
            .add(nuevoMensaje.toMap())
            .addOnSuccessListener {
                // Aquí puedes añadir lógica adicional si se envió el mensaje exitosamente
            }
            .addOnFailureListener { e ->
                // Aquí puedes manejar el error si hubo un problema al enviar el mensaje
            }
    }


    private val _urlFotoPerfil = MutableLiveData<String?>(null)
    val urlFotoPerfil: LiveData<String?> get() = _urlFotoPerfil

    private val db = FirebaseFirestore.getInstance()
    fun fetchFotoPerfil(fotoPerfilId: String) {
        if (fotoPerfilId.isNotEmpty()) {
            db.collection("imagenes").document(fotoPerfilId).get().addOnSuccessListener { documentSnapshot ->
                val url = documentSnapshot.getString("url")
                _urlFotoPerfil.value = url
            }.addOnFailureListener { e ->
                Log.e("fetchFotoPerfil", "Error al obtener la foto de perfil: ${e.message}")
            }
        }
    }

    fun obtenerImagenesFotoPerfil(): LiveData<List<Imagenes>> {
        val liveData = MutableLiveData<List<Imagenes>>()
        val db = FirebaseFirestore.getInstance()
        db.collection("imagenes")
            .whereEqualTo("categoria", "emoji")
            .get()
            .addOnSuccessListener { result ->
                val images = result.map { it.toObject(Imagenes::class.java) }
                liveData.value = images
            }.addOnFailureListener { e ->
                Log.e("obtenerImagenesFotoPerfil", "Error al obtener imágenes de foto de perfil: ${e.message}")
            }
        return liveData
    }

    fun actualizarFotoPerfil(nuevaFotoPerfilId: String, canalId: String?) {
        if (canalId != null) {
            db.collection("canales").document(canalId).update("imagenId", nuevaFotoPerfilId)
                .addOnSuccessListener {
                    Log.d("actualizarFotoPerfil", "Foto de perfil actualizada en Firestore")
                    fetchFotoPerfil(nuevaFotoPerfilId)
                }
                .addOnFailureListener { e ->
                    Log.e("actualizarFotoPerfil", "Error al actualizar la foto de perfil en Firestore: ${e.message}")
                }
        }
    }

    fun actualizarNombre(nuevoNombre: String, canalId: String?) {
        if (canalId != null) {
            db.collection("canales").document(canalId).update("nombreCanal", nuevoNombre)
                .addOnSuccessListener {
                    Log.d("actualizarNombre", "Nombre actualizado en Firestore")
                    obtenerCanalPorId(canalId)
                }
                .addOnFailureListener { e ->
                    Log.e("actualizarNombre", "Error al actualizar el nombre en Firestore: ${e.message}")
                }
        }
    }

    fun obtenerUsuariosDelCanal(canalId: String?): LiveData<List<Usuarios>> {
        val liveData = MutableLiveData<List<Usuarios>>()
        val db = FirebaseFirestore.getInstance()

        canalId?.let { id ->
            db.collection("canales").document(id).get().addOnSuccessListener { document ->
                val profesorId = document.getString("profesorId")
                val estudiantes = document.get("estudiantes") as? List<String> ?: listOf()

                // Crear una lista para guardar todos los Usuarios
                val usuariosList = mutableListOf<Usuarios>()

                // Obtener el profesor
                profesorId?.let {
                    canalViewModel.obtenerUsuarioPorId(it).observeForever { profesor ->
                        profesor?.let {
                            usuariosList.add(it)

                            // Verificar si hemos terminado de agregar todos los usuarios
                            if (usuariosList.size == estudiantes.size + 1) {
                                liveData.value = usuariosList
                            }
                        }
                    }
                }

                // Obtener estudiantes
                for (estudianteId in estudiantes) {
                    canalViewModel.obtenerUsuarioPorId(estudianteId).observeForever { estudiante ->
                        estudiante?.let {
                            usuariosList.add(it)

                            // Verificar si hemos terminado de agregar todos los usuarios
                            if (usuariosList.size == estudiantes.size + 1) {
                                liveData.value = usuariosList
                            }
                        }
                    }
                }
            }
        }

        return liveData
    }


    fun eliminarCanal(canalId: String?, onSuccess: () -> Unit, onFailure: (Exception) -> Unit) {
        val db = FirebaseFirestore.getInstance()
        val canalRef = canalId?.let { db.collection("canales").document(it) }

        // Paso 1: Eliminar el documento del canal
        if (canalRef != null) {
            canalRef.delete().addOnSuccessListener {

                // Paso 2: Eliminar todos los mensajes que pertenecen a ese canal
                val mensajesRef = db.collection("mensajes")
                mensajesRef.whereEqualTo("canalID", canalId)
                    .get()
                    .addOnSuccessListener { querySnapshot ->
                        val batch = db.batch()
                        for (document in querySnapshot.documents) {
                            batch.delete(document.reference)
                        }

                        // Ejecutar batch para eliminar todos los mensajes
                        batch.commit().addOnSuccessListener {

                            // Paso 3: Eliminar canalId de la lista "canales" de cada usuario
                            val usersRef = db.collection("users")
                            usersRef.whereArrayContains("canales", canalId)
                                .get()
                                .addOnSuccessListener { userQuerySnapshot ->
                                    val userBatch = db.batch()
                                    for (userDocument in userQuerySnapshot.documents) {
                                        userBatch.update(userDocument.reference, "canales", FieldValue.arrayRemove(canalId))
                                    }

                                    // Ejecutar batch para actualizar usuarios
                                    userBatch.commit().addOnSuccessListener {
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
                    }.addOnFailureListener { exception ->
                        onFailure(exception)
                    }
            }.addOnFailureListener { exception ->
                onFailure(exception)
            }
        }
    }


    /*fun eliminarCanal(canalId: String?) {
        val db = FirebaseFirestore.getInstance()

        val mensajesQuery = db.collection("mensajes").whereEqualTo("canalID", canalId)
        val usuariosQuery = db.collection("users").whereArrayContains("canales", canalId!!)

        // Primero obtenemos los mensajes
        mensajesQuery.get().continueWithTask { task ->
            if (task.isSuccessful) {
                // Luego obtenemos los usuarios
                usuariosQuery.get()
            } else {
                throw task.exception!!
            }
        }.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val usuariosSnapshot = task.result
                val mensajesSnapshot = mensajesQuery.get().result

                // Iniciar transacción
                db.runTransaction { transaction ->
                    // Eliminar los mensajes relacionados con el canal
                    for (mensajeDocument in mensajesSnapshot!!.documents) {
                        transaction.delete(mensajeDocument.reference)
                    }

                    // Actualizar los usuarios eliminando el canalId de su lista "canales"
                    for (usuarioDocument in usuariosSnapshot!!.documents) {
                        transaction.update(usuarioDocument.reference, "canales", FieldValue.arrayRemove(canalId))
                    }

                    // Borrar el documento canal
                    val canalRef = db.collection("canales").document(canalId)
                    transaction.delete(canalRef)
                }.addOnSuccessListener {
                    // Transacción exitosa
                    Log.d("EliminarCanal", "Canal eliminado exitosamente!")
                }.addOnFailureListener { e ->
                    // En caso de error
                    Log.e("ErrorEliminandoCanal", "Error al eliminar canal", e)
                }
            } else {
                Log.e("ErrorEliminandoCanal", "Error al obtener datos", task.exception)
            }
        }
    }*/


    /*fun eliminarCanal(canalId: String?) {
        val db = FirebaseFirestore.getInstance()

        // Iniciar transacción
        db.runTransaction { transaction ->

            // 1. Obtener y eliminar todos los mensajes pertenecientes a ese canal.
            obtenerMensajesPorCanal(canalId)
            this.mensajes.value?.forEach { mensaje ->
                val mensajeRef = mensaje.id?.let { db.collection("mensajes").document(it) }
                if (mensajeRef != null) {
                    transaction.delete(mensajeRef)
                }
            }

            // 2. Consultar todos los usuarios que pertenecen a ese canal y eliminar a todos estos usuarios,
            // de la propiedad canales, el id canal que estamos eliminando.
            val usuariosLiveData = obtenerUsuariosDelCanal(canalId)
            usuariosLiveData.value?.forEach { usuario ->
                val userRef = usuario.id?.let { db.collection("users").document(it) }
                if (userRef != null) {
                    transaction.update(userRef, "canales", FieldValue.arrayRemove(canalId))
                }
            }

            // 3. Borrar el documento canal.
            val canalRef = db.collection("canales").document(canalId!!)
            transaction.delete(canalRef)

        }.addOnSuccessListener {
            // Transacción exitosa.
            Log.d("EliminarCanal", "Canal eliminado exitosamente!")

        }.addOnFailureListener { e ->
            // En caso de error.
            Log.e("ErrorEliminandoCanal", "Error al eliminar canal", e)
        }
    }*/

}