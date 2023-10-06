package com.example.teacherconnect.interfaces.canal

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import androidx.navigation.NavController
import com.example.teacherconnect.R

@Composable
fun Home_CanalScreen(navController: NavController) {
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
        Image(
            painter = painterResource(id = R.drawable.logo_blanco),
            contentDescription = null,
            modifier = Modifier.align(Alignment.TopCenter)
                .padding(top = 17.dp).width(230.dp)
                .height(170.dp)
        )

        // Botón "Volver"
        TextButton(
            onClick = {},
            modifier = Modifier.align(Alignment.TopStart).padding(start = 30.dp, top = 200.dp)
        ) {
            Text("< Volver", color = Color.White, fontSize = 18.sp)
        }

        // Layout de los botones centrales
        Column(
            modifier = Modifier.align(Alignment.Center)
                .padding(top = 65.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            ButtonWithIconAndText(
                imageRes = R.drawable.icono_canales,
                text = "Tus canales",
                onClick = {}
            )
            ButtonWithIconAndText(
                imageRes = R.drawable.icono_avisos,
                text = "Tus avisos",
                onClick = {}
            )
            ButtonWithIconAndText(
                imageRes = R.drawable.icono_nuevo_canal,
                text = "Crear un nuevo canal",
                onClick = {}
            )
        }

        // Botón de soporte
        Image(
            painter = painterResource(id = R.drawable.icono_soporte),
            contentDescription = null,
            modifier = Modifier.align(Alignment.BottomEnd).padding(16.dp)
        )
    }
}

@Composable
fun ButtonWithIconAndText(imageRes: Int, text: String, onClick: () -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
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
            modifier = Modifier.size(80.dp) // Ajusta para que las imágenes sean más grandes
        )
        Text(text = text, color = Color.White, fontSize = 24.sp) // Aumento el tamaño de fuente para que sea más grande
    }
}
