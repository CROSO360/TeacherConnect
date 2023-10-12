package com.example.teacherconnect.interfaces.horario


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
fun FondoActividades(
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
fun HorarioActividadesScreen(navController: NavController) {
    FondoActividades {
        Image(
            painter = painterResource(id = R.drawable.fondo2),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        val customColor = Color(0xFF2586AF)
        val cCustomColor = Color(0xFFFFFFFF)
        val ccCustomColor = Color(0xFF495765)
        val transparentButtonColors = ButtonDefaults.buttonColors(cCustomColor)
        val transparentButtoncColors = ButtonDefaults.buttonColors(ccCustomColor)

        Box(modifier = Modifier.fillMaxSize()) {
            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 16.dp),
                    verticalArrangement = Arrangement.Center
                ) {
                    Button(modifier = Modifier
                        .offset(x = (165).dp, y = (16).dp)
                        .width(180.dp)
                        .height(38.dp)
                        .padding(bottom = 4.dp, start = 4.dp),
                        onClick = { /* Navigate to Lunes */ },
                        colors = transparentButtonColors
                    )
                    {
                        Text(
                            text ="Lunes",
                            style = TextStyle(fontWeight = FontWeight.Bold,fontSize = 13.2.sp),
                            color = customColor
                        )
                    }
                    Button(modifier = Modifier
                        .offset(x = (165).dp, y = (16).dp)
                        .width(180.dp)
                        .height(38.dp)
                        .padding(bottom = 4.dp, start = 4.dp),
                        onClick = { /* Navigate to Lunes */ },
                        colors = transparentButtonColors
                    )
                    {
                        Text(
                            text ="Martes",
                            style = TextStyle(fontWeight = FontWeight.Bold,fontSize = 13.2.sp),
                            color = customColor
                        )
                    }
                    Button(modifier = Modifier
                        .offset(x = (165).dp, y = (16).dp)
                        .width(180.dp)
                        .height(38.dp)
                        .padding(bottom = 4.dp, start = 4.dp),
                        onClick = { /* Navigate to Lunes */ },
                        colors = transparentButtonColors
                    )
                    {
                        Text(
                            text ="Miercoles",
                            style = TextStyle(fontWeight = FontWeight.Bold,fontSize = 13.2.sp),
                            color = customColor
                        )
                    }
                    Button(modifier = Modifier
                        .offset(x = (165).dp, y = (16).dp)
                        .width(180.dp)
                        .height(38.dp)
                        .padding(bottom = 4.dp, start = 4.dp),
                        onClick = { /* Navigate to Lunes */ },
                        colors = transparentButtonColors
                    )
                    {
                        Text(
                            text ="Jueves",
                            style = TextStyle(fontWeight = FontWeight.Bold,fontSize = 13.2.sp),
                            color = customColor
                        )
                    }
                    Button(modifier = Modifier
                        .offset(x = (165).dp, y = (16).dp)
                        .width(180.dp)
                        .height(38.dp)
                        .padding(bottom = 4.dp, start = 4.dp),
                        onClick = { /* Navigate to Lunes */ },
                        colors = transparentButtonColors
                    )
                    {
                        Text(
                            text ="Viernes",
                            style = TextStyle(fontWeight = FontWeight.Bold,fontSize = 13.2.sp),
                            color = customColor
                        )
                    }
                    Button(modifier = Modifier
                        .offset(x = (165).dp, y = (16).dp)
                        .width(180.dp)
                        .height(38.dp)
                        .padding(bottom = 4.dp, start = 4.dp),
                        onClick = { /* Navigate to Lunes */ },
                        colors = transparentButtonColors
                    )
                    {
                        Text(
                            text ="Sabado",
                            style = TextStyle(fontWeight = FontWeight.Bold,fontSize = 13.2.sp),
                            color = customColor
                        )
                    }
                    Button(modifier = Modifier
                        .offset(x = (165).dp, y = (16).dp)
                        .width(180.dp)
                        .height(38.dp)
                        .padding(bottom = 4.dp, start = 4.dp),
                        onClick = { /* Navigate to Lunes */ },
                        colors = transparentButtonColors
                    )
                    {
                        Text(
                            text ="Domingo",
                            style = TextStyle(fontWeight = FontWeight.Bold,fontSize = 13.2.sp),
                            color = customColor
                        )
                    }
                }
            }

            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            )
            {
                Box(
                    modifier = Modifier
                        .offset(y = 120.dp)
                        .width(355.dp)
                        .height(325.dp)
                        .clip(RoundedCornerShape(20.dp))
                        .background(cCustomColor)
                )
            }
            Box(
                    modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
            ) {
            Text(modifier = Modifier
                .offset(y = -80.dp),
                text = "Lunes",
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp,
                    color = Color(0xFF1FE7D2)
                )
            )

        }
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(modifier = Modifier
                    .offset(x = (-125).dp, y = (-10).dp),
                    text = "Hora",
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp,
                        color = Color(0xFF1FE7D2)
                    )
                )
                Text(modifier = Modifier
                    .offset(x = (-125).dp, y = (50).dp),
                    text = "Hora",
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp,
                        color = Color(0xFF1FE7D2)
                    )
                )
                Text(modifier = Modifier
                    .offset(x = (-125).dp, y = (110).dp),
                    text = "Hora",
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp,
                        color = Color(0xFF1FE7D2)
                    )
                )
                Text(modifier = Modifier
                    .offset(x = (-125).dp, y = (170).dp),
                    text = "Hora",
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp,
                        color = Color(0xFF1FE7D2)
                    )
                )
                Text(modifier = Modifier
                    .offset(x = (-125).dp, y = (230).dp),
                    text = "Hora",
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp,
                        color = Color(0xFF1FE7D2)
                    )
                )

            }

            Button(
                onClick = { navController.navigate(Pantallas.Horario_homeConexion.name)},
                modifier = Modifier.align(Alignment.BottomCenter).padding(16.dp),
                colors = transparentButtoncColors
            ) {
                Text(
                    text="Salir",
                    style = TextStyle(fontWeight = FontWeight.Bold),
                    color = Color(0xFF1FE7D2 )
                )
            }
        }

    }
}