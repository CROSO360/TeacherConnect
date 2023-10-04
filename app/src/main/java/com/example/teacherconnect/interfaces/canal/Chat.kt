package com.example.teacherconnect.interfaces.canal

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.teacherconnect.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatScreen() {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        // 1. Fondo de la pantalla
        Image(
            painter = painterResource(id = R.drawable.fondo_chat),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        // 2. Barra superior
        TopAppBar(
            title = {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.emoji1),
                        contentDescription = null,
                        modifier = Modifier.size(40.dp).clip(CircleShape).background(color = Color.White)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = "Moviles 7A")
                }
            },
            navigationIcon = {
                IconButton(onClick = { /* Ir atrás */ }) {
                    Icon(Icons.Default.ArrowBack, contentDescription = null)
                }
            },
            actions = {
                IconButton(onClick = { /* Mostrar opciones */ }) {
                    Icon(Icons.Default.MoreVert, contentDescription = null)
                }
            }
        )

        // 3. Mensajes del chat
        LazyColumn(
            modifier = Modifier.padding(bottom = 60.dp),
            reverseLayout = true
        ) {
            /*items(/* lista de mensajes */) { mensaje ->
                // Representar cada mensaje
            }*/
        }

        // 4. Barra inferior para chatear
        Box(
            modifier = Modifier.align(Alignment.BottomCenter).fillMaxWidth().padding(8.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Color(29,32,41)) // Aquí eliges el color de fondo de la barra del chat
                    .padding(horizontal = 8.dp, vertical = 12.dp)
                    .align(Alignment.BottomCenter),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                IconButton(onClick = { /* abrir selector de emojis */ }) {
                    Image(
                        painter = painterResource(id = R.drawable.icono_chat), // Asegúrate de reemplazar 'icono_emoji' con el nombre correcto de tu recurso.
                        contentDescription = "Seleccionar emoji"
                    )
                }

                TextField(
                    value = "binding del texto" ,
                    onValueChange = { /* actualizar el texto */ },
                    modifier = Modifier.weight(1f).padding(start = 8.dp, end = 8.dp)
                        .background(color = Color.Black),
                    placeholder = { Text(text = "Mensaje") }
                )

                IconButton(onClick = { /* enviar mensaje */ }) {
                    Icon(Icons.Default.Send, contentDescription = "Enviar mensaje")
                }
            }
        }

    }
}