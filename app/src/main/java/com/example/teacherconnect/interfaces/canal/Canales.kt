package com.example.teacherconnect.interfaces.canal

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.teacherconnect.R

@Composable
fun ChannelScreen(){
    Box(
        modifier = Modifier.fillMaxSize().background(Color.Black)
    ) {
        // Imagen de las bolas amarillas
        Image(
            painter = painterResource(id = R.drawable.fondo),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        // Logo "TC"
        Text(
            "Tus\ncanales", color = Color.White, fontSize = 40.sp,
            lineHeight = 50.sp,
            modifier = Modifier
                .padding(top = 115.dp)
                .padding(start = 45.dp)
        )

        /*Image(
            painter = painterResource(id = R.drawable.logo_blanco),
            contentDescription = null,
            modifier = Modifier/*.align(Alignment.TopCenter)*/.padding(top = 17.dp)
                .width(230.dp).height(170.dp)
        )*/

        // Botón "Volver"
        TextButton(
            onClick = {},
            modifier = Modifier.align(Alignment.TopStart).padding(start = 30.dp, top = 35.dp)
        ) {
            Text("< Volver", color = Color.White, fontSize = 18.sp)
        }

        // Layout de los botones centrales
        Column(
            modifier = Modifier.align(Alignment.Center).padding(top = 100.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(5.dp),
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp).padding(start = 50.dp)
            ) {
                ChannelScreenButtons(
                    imageRes = R.drawable.emoji1,
                    text = "Moviles 7A",
                    onClick = {}
                )
                ChannelScreenButtons(
                    imageRes = R.drawable.emoji2,
                    text = "Moviles 7B",
                    onClick = {}
                )
            }
            Row(
                horizontalArrangement = Arrangement.spacedBy(5.dp),
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp).padding(start = 50.dp)
            ) {
                ChannelScreenButtons(
                    imageRes = R.drawable.emoji3,
                    text = "Objetos 3A",
                    onClick = {}
                )
                ChannelScreenButtons(
                    imageRes = R.drawable.emoji4,
                    text = "Objetos 3B",
                    onClick = {}
                )
            }
            Row(
                horizontalArrangement = Arrangement.spacedBy(5.dp),
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp).padding(start = 50.dp)
            ) {
                ChannelScreenButtons(
                    imageRes = R.drawable.emoji5,
                    text = "Peloteo 1.0",
                    onClick = {}
                )
                ChannelScreenButtons(
                    imageRes = R.drawable.emoji6,
                    text = "Peloteo 2.0",
                    onClick = {}
                )
            }
        }

        // Botón de soporte
        Image(
            painter = painterResource(id = R.drawable.icono_soporte),
            contentDescription = null,
            modifier = Modifier.align(Alignment.BottomEnd).padding(5.dp)
        )
    }
}

@Composable
fun ChannelScreenButtons(imageRes: Int, text: String, onClick: () -> Unit) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.clickable(onClick = onClick).padding(7.dp)
            .background(
                color = Color.Transparent,
                shape = MaterialTheme.shapes.medium
            )
            .padding(12.dp)
    ) {
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = null,
            modifier = Modifier.size(80.dp)
        )
        Text(text = text, color = Color.White, fontSize = 17.sp)
    }
}
