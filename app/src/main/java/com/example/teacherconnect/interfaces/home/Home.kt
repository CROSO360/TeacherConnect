package com.example.teacherconnect.interfaces.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.teacherconnect.R

@Composable
fun Home(navController: NavController){
    GradientBackground {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo_negro),
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .size(100.dp)
                    .padding(top = 20.dp, start = 20.dp)
            )
            Image(
                painter = painterResource(id = R.drawable.icono_avisos),
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .size(100.dp)
                    .padding(top = 20.dp, end = 20.dp)
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "¡Bienvenido a",
                    modifier = Modifier.padding(top = 100.dp),
                    style = TextStyle(fontSize = 32.sp, color = Color.Black)
                )
                Text(
                    text = "TeacherConnect!",
                    style = TextStyle(fontSize = 36.sp, color = Color.Black, fontWeight = FontWeight.Bold)
                )
                Text(
                    text = "Próxima Actividad",
                    modifier = Modifier.padding(top = 50.dp),
                    style = TextStyle(fontSize = 20.sp, color = Color.DarkGray, fontWeight = FontWeight.Bold),
                    textAlign = TextAlign.Start
                )
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 40.dp, end = 40.dp, bottom = 30.dp, top = 10.dp)
                        .clip(RoundedCornerShape(12.dp)),
                    color = Color.White
                ) {
                    Row(
                        modifier = Modifier.padding(12.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column {
                            Text(
                                text = "LUNES",
                                color = Color(0xFFD32CE6),
                                fontWeight = FontWeight.Bold,
                                style = TextStyle(fontSize = 16.sp)
                            )
                            Text(
                                text = "9:00AM",
                                color = Color(0xFFD32CE6),
                                style = TextStyle(fontSize = 14.sp)
                            )
                        }
                        Column(
                            horizontalAlignment = Alignment.End
                        ) {
                            Text(
                                text = "203",
                                fontWeight = FontWeight.Bold,
                                style = TextStyle(fontSize = 22.sp, color = Color.Black)
                            )
                            Text(
                                text = "APLICACIONES MÓVILES",
                                style = TextStyle(fontSize = 14.sp, color = Color.Gray)
                            )
                        }
                    }
                }
                Text(
                    text = "Centro de Control",
                    modifier = Modifier,
                    style = TextStyle(fontSize = 20.sp, color = Color.DarkGray, fontWeight = FontWeight.Bold),
                    textAlign = TextAlign.Start
                )
                Spacer(modifier = Modifier.height(20.dp))
                Image(
                    painter = painterResource(id = R.drawable.sistema_avisos),
                    contentDescription = null,
                    modifier = Modifier.size(110.dp)
                )
                Text(text = "Sistema de Avisos", style = TextStyle(fontSize = 18.sp, color = Color.White, fontWeight = FontWeight.Bold))
                Spacer(modifier = Modifier.height(40.dp))
                Image(
                    painter = painterResource(id = R.drawable.gestiona_horario),
                    contentDescription = null,
                    modifier = Modifier.size(100.dp)
                )
                Text(text = "Gestiona tu Horario", style = TextStyle(fontSize = 18.sp, color = Color.White, fontWeight = FontWeight.Bold))
            }
            Image(
                painter = painterResource(id = R.drawable.salir),
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .size(60.dp)
                    .padding(bottom = 20.dp, start = 20.dp)
            )
            Image(
                painter = painterResource(id = R.drawable.icono_soporte),
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .size(60.dp)
                    .padding(bottom = 20.dp, end = 20.dp)
            )
        }
    }
}
@Composable
fun GradientBackground(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFFF6F7F9),
                        Color(0xFF2D5A84)
                    )
                )
            )
    ) {
        content()
    }
}