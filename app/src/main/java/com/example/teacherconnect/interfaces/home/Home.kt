package com.example.teacherconnect.interfaces.home

import androidx.activity.compose.BackHandler
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
import com.example.teacherconnect.LocalBackgroundGradient
import com.example.teacherconnect.LocalIsDarkMode
import com.example.teacherconnect.LocalTextColor
import com.example.teacherconnect.R
import com.example.teacherconnect.interfaces.login.LoginViewModel
import com.example.teacherconnect.navegacion.Pantallas
import kotlin.system.exitProcess

@Composable
fun Home(navController: NavController,viewModel: LoginViewModel =androidx.lifecycle.viewmodel.compose.viewModel()){
    GradientBackground {
        val isDarkMode = LocalIsDarkMode.current
        val textColors =LocalTextColor.current
        BackHandler {
            exitProcess(0)
        }
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Image(
                painter = painterResource(id = if (isDarkMode.value) R.drawable.logo_blanco else R.drawable.logo_negro),
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .size(130.dp)
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
                    style = TextStyle(fontSize = 32.sp, color = textColors.value)
                )
                Text(
                    text = "TeacherConnect!",
                    style = TextStyle(fontSize = 36.sp, color = textColors.value, fontWeight = FontWeight.Bold)
                )
                Text(
                    text = "Próxima Actividad",
                    modifier = Modifier.padding(top = 50.dp),
                    style = TextStyle(fontSize = 20.sp, color = textColors.value, fontWeight = FontWeight.Bold),
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
                    style = TextStyle(fontSize = 20.sp, color = textColors.value, fontWeight = FontWeight.Bold),
                    textAlign = TextAlign.Start
                )
                Spacer(modifier = Modifier.height(20.dp))
                Image(
                    painter = painterResource(id = R.drawable.sistema_avisos),
                    contentDescription = null,
                    modifier = Modifier
                        .size(110.dp)
                        .clickable {
                            navController.navigate(Pantallas.Home_CanalConexion.name)
                        }
                )
                Text(text = "Sistema de Avisos", style = TextStyle(fontSize = 18.sp, color = Color.White, fontWeight = FontWeight.Bold))
                Spacer(modifier = Modifier.height(40.dp))
                Image(
                    painter = painterResource(id = R.drawable.gestiona_horario),
                    contentDescription = null,
                    modifier = Modifier
                        .size(100.dp)
                        .clickable {
                            navController.navigate(Pantallas.Home_HorarioConexion.name)
                        }
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
                    .clickable {
                        viewModel.logout()
                        navController.navigate(Pantallas.LoginConexion.name) {
                            popUpTo(Pantallas.HomeConexion.name) { inclusive = true }
                        }
                    }
            )
            Image(
                painter = painterResource(id = R.drawable.icon_ajustes),
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .size(80.dp)
                    .padding(bottom = 20.dp, end = 20.dp)
                    .clickable {
                        navController.navigate(Pantallas.ConfiguracionConexion.name)
                    }
            )
        }
    }
}
@Composable
fun GradientBackground(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    val gradientColors = LocalBackgroundGradient.current
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(colors = gradientColors.value)

            )
    ) {
        content()
    }
}