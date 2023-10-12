package com.example.teacherconnect.interfaces.horario

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.teacherconnect.R
import com.example.teacherconnect.navegacion.Pantallas

@Composable
fun FondoVer(
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
fun HorarioVerScreen(navController: NavController) {
    FondoVer {
        Image(
            painter = painterResource(id = R.drawable.fondo2),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        Image(
            painter = painterResource(id = R.drawable.icon),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .offset(x = (185).dp, y = (16).dp)
                .width(160.dp)
                .height(160.dp)
        )
        val customColor = Color(0xFF2586AF)
        val cCustomColor = Color(0xFFFFFFFF)
        val ccCustomColor = Color(0xFF495765)
        val transparentButtonColors = ButtonDefaults.buttonColors(cCustomColor)
        val transparentButtoncColors = ButtonDefaults.buttonColors(ccCustomColor)
        Text(
            text = "Escoge un dia de la semana para visualizar tu horario",
            color = customColor,
            style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 13.2.sp),
            modifier = Modifier
                .fillMaxSize()
                .offset(x = (60).dp, y = (280).dp),

            )
        Button(
            modifier = Modifier
                .offset(x = (90).dp, y = (329).dp)
                .width(225.dp)
                .height(38.dp)
                .padding(bottom = 4.dp, start = 4.dp),
            onClick = { navController.navigate(Pantallas.Horario_actividadesConexion.name) },
            colors = transparentButtonColors
        ) {
            Text(
                text = "Lunes",
                style = TextStyle(fontWeight = FontWeight.Bold,fontSize = 13.2.sp),
                color = customColor

                )
        }
        Button(
            modifier = Modifier
                .offset(x = (90).dp, y = (379).dp)
                .width(225.dp)
                .height(38.dp)
                .padding(bottom = 4.dp, start = 4.dp),
            onClick = { navController.navigate(Pantallas.Horario_actividadesConexion.name) },
            colors = transparentButtonColors
        ) {
            Text(
                text = "Martes",
                style = TextStyle(fontWeight = FontWeight.Bold),
                color = customColor
                )
        }
        Button(
            modifier = Modifier
                .offset(x = (90).dp, y = (429).dp)
                .width(225.dp)
                .height(38.dp)
                .padding(bottom = 4.dp, start = 4.dp),
            onClick = { navController.navigate(Pantallas.Horario_actividadesConexion.name) },
            colors = transparentButtonColors
        ) {
            Text(
                text = "Miercoles",
                style = TextStyle(fontWeight = FontWeight.Bold),
                color = customColor
                )
        }
        Button(
            modifier = Modifier
                .offset(x = (90).dp, y = (479).dp)
                .width(225.dp)
                .height(38.dp)
                .padding(bottom = 4.dp, start = 4.dp),
            onClick = { navController.navigate(Pantallas.Horario_actividadesConexion.name) },
            colors = transparentButtonColors
        ) {
            Text(
                text = "Jueves",
                style = TextStyle(fontWeight = FontWeight.Bold),
                color = customColor
                )
        }
        Button(
            modifier = Modifier
                .offset(x = (90).dp, y = (529).dp)
                .width(225.dp)
                .height(38.dp)
                .padding(bottom = 4.dp, start = 4.dp),
            onClick = { navController.navigate(Pantallas.Horario_actividadesConexion.name) },
            colors = transparentButtonColors
        ) {
            Text(
                text = "Viernes",
                style = TextStyle(fontWeight = FontWeight.Bold),
                color = customColor
                )
        }
        Button(
            modifier = Modifier
                .offset(x = (90).dp, y = (579).dp)
                .width(225.dp)
                .height(38.dp)
                .padding(bottom = 4.dp, start = 4.dp),
            onClick = { navController.navigate(Pantallas.Horario_actividadesConexion.name) },
            colors = transparentButtonColors
        ) {
            Text(
                text = "Sabado",
                style = TextStyle(fontWeight = FontWeight.Bold),
                color = customColor
            )
        }
        Button(
            modifier = Modifier
                .offset(x = (90).dp, y = (629).dp)
                .width(225.dp)
                .height(38.dp)
                .padding(bottom = 4.dp, start = 4.dp),
            onClick = { navController.navigate(Pantallas.Horario_actividadesConexion.name) },
            colors = transparentButtonColors
        ) {
            Text(
                text = "Domingo",
                style = TextStyle(fontWeight = FontWeight.Bold),
                color = customColor
            )
        }
        Button(
            modifier = Modifier
                .offset(y = 750.dp)
                .padding(bottom = 16.dp, start = 18.dp),
            onClick = { navController.popBackStack() },
            colors = transparentButtoncColors
        ) {
            Text(
                text = "<Volver",
                style = TextStyle(fontWeight = FontWeight.Bold),
                color = customColor
                )
        }

    }
}