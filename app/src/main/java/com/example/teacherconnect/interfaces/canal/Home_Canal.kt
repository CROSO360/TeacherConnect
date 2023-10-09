package com.example.teacherconnect.interfaces.canal

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.teacherconnect.R
import com.example.teacherconnect.firebase.Canales
import com.example.teacherconnect.interfaces.login.LoginViewModel
import com.example.teacherconnect.navegacion.Pantallas
import com.google.firebase.auth.FirebaseAuth

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home_CanalScreen(navController: NavController) {
    val showDialog = rememberSaveable { mutableStateOf(false) }
    val canalName = rememberSaveable { mutableStateOf("") }
    val canalviewmodel = CanalViewModel()
    val auth = FirebaseAuth.getInstance()


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        // Imagen de las bolas amarillas
        Image(
            painter = painterResource(id = R.drawable.fondo),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        // Logo "TC"
        Image(
            painter = painterResource(id = R.drawable.logo_blanco),
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(top = 17.dp, start = 30.dp)
                .width(230.dp)
                .height(170.dp)
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Center
        ) {
            // Botón "Volver"
            Text(text = "< Volver", color = Color.White, fontSize = 18.sp,
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(start=30.dp)
                    .clickable {navController.navigate(Pantallas.HomeConexion.name)}
            )
            Spacer(modifier = Modifier.height(20.dp))

            // Fila con los botones "Tus canales" y "Tus avisos"
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 5.dp)
            ) {
                // Botón "Tus canales"
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Image(
                        painter = painterResource(id = R.drawable.icono_canales),
                        contentDescription = null,
                        modifier = Modifier
                            .size(130.dp)
                            .clickable {
                                navController.navigate(Pantallas.TusCanalesConexion.name)
                            }
                    )
                    Text(
                        text = "Tus canales",
                        style = TextStyle(
                            fontSize = 18.sp,
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )
                    )
                }
                Spacer(modifier = Modifier.width(60.dp))

                // Botón "Tus avisos"
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Image(
                        painter = painterResource(id = R.drawable.icono_avisos),
                        contentDescription = null,
                        modifier = Modifier
                            .size(130.dp)
                            .clickable {
                            }
                    )
                    Text(
                        text = "Tus avisos",
                        style = TextStyle(
                            fontSize = 18.sp,
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Botón "Crear un nuevo canal"
            Column(horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.align(Alignment.CenterHorizontally)) {
                Image(
                    painter = painterResource(id = R.drawable.icono_nuevo_canal),
                    contentDescription = null,
                    modifier = Modifier
                        .size(130.dp)
                        .clickable {
                            showDialog.value = true
                        }
                )
                Text(
                    text = "Crear un nuevo canal",
                    style = TextStyle(
                        fontSize = 18.sp,
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                )
            }
        }
        if (showDialog.value) {
            AlertDialog(
                onDismissRequest = { showDialog.value = false },
                title = { Text(text = "Crear un nuevo canal") },
                text = {
                    TextField(
                        value = canalName.value,
                        onValueChange = { canalName.value = it },
                        label = { Text("Nombre del canal") }
                    )
                },
                confirmButton = {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        horizontalArrangement = Arrangement.End,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Button(onClick = {
                            showDialog.value = false
                            canalName.value = ""
                        }) {
                            Text("Cancelar")
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        Button(onClick = {
                            val usuarioId = auth.currentUser?.uid ?: return@Button

                            // Crear una instancia de Canal
                            val canal = Canales(
                                id = null,
                                nombreCanal = canalName.value,
                                usuarioId = usuarioId
                            )

                            canalviewmodel.createCanal(
                                canal,
                                onSuccess = { id ->
                                    Log.d("TeacherConnect", "Canal creado: $id")
                                    canalviewmodel.addCanalToUser(usuarioId, id)
                                    canalName.value = ""
                                    showDialog.value = false
                                },
                                onFailure = {
                                    Log.d("TeacherConnect", "Error al crear canal: ${it}")
                                }
                            )
                        }) {
                            Text("Crear")
                        }
                    }
                }


            )
        }


        // Botón de soporte
        Image(
            painter = painterResource(id = R.drawable.icono_soporte),
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)
        )
    }
}


