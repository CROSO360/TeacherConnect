package com.example.teacherconnect.interfaces.horario

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.teacherconnect.R
import com.example.teacherconnect.navegacion.Pantallas

@Composable
fun FondoHome(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF0E0A0B),
                        Color(0xFF495765)
                    )
                )
            )
    ) {
        content()
    }
}
@Composable
fun HorarioHomeScreen(navController: NavController) {
    FondoHome {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .offset(y = 165.dp),
            contentAlignment = Alignment.Center
        ) {
            Button(
                onClick = { navController.navigate(Pantallas.Horario_formulario1Conexion.name) },
                colors = ButtonDefaults.run { buttonColors(Color.Transparent) },
                contentPadding = PaddingValues(0.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.crear),
                    contentDescription = "Descripción de la imagen",
                    modifier = Modifier
                        .width(170.dp)
                        .height(160.dp)
                )
            }

        }
        Image(
            painter = painterResource(id = R.drawable.icon),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .offset(x = (185).dp, y = (16).dp)
                .width(160.dp)
                .height(160.dp)
        )
        Image(
            painter = painterResource(id = R.drawable.fondo2),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomStart
        ) {
            Text(
                text = "< Volver",
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(27.dp)
                    .clickable(onClick = { })
            )


        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .offset(x = (100).dp, y = (-35).dp),
            contentAlignment = Alignment.Center
        ) {
            Button(
                onClick = { },
                colors = ButtonDefaults.run { buttonColors(Color.Transparent) },
                contentPadding = PaddingValues(0.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.mod),
                    contentDescription = "Descripción de la imagen",
                    modifier = Modifier
                        .width(170.dp)
                        .height(160.dp)
                )
            }

        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .offset(x = (-100).dp, y = (-35).dp),
            contentAlignment = Alignment.Center
        ) {
            Button(
                onClick = { navController.navigate(Pantallas.Horario_verConexion.name) },
                colors = ButtonDefaults.run { buttonColors(Color.Transparent) },
                contentPadding = PaddingValues(0.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ver),
                    contentDescription = "Descripción de la imagen",
                    modifier = Modifier
                        .width(170.dp)
                        .height(160.dp)
                )
            }

        }
    }
}

