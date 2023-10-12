package com.example.teacherconnect.interfaces.horario


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
fun FondoGestion(
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HorarioFormulario1Screen(navController: NavController) {
    FondoGestion {
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
        val customColor = Color(0xFF20D3CD)
        val cCustomColor = Color(0xFF747474)
        val transparentButtonColors = ButtonDefaults.buttonColors(cCustomColor)
        val commentValue = remember { mutableStateOf("") }
        val customTextFieldColors = TextFieldDefaults.textFieldColors(
            textColor = Color.White,
            cursorColor = customColor,
            focusedIndicatorColor = customColor,
            unfocusedIndicatorColor = customColor,
            disabledIndicatorColor = customColor,
            errorIndicatorColor = customColor,
            focusedLabelColor = customColor,
            unfocusedLabelColor = customColor,
            disabledLabelColor = customColor,
            errorLabelColor = customColor
        )

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .width(355.dp)
                    .height(290.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .background(cCustomColor),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(20.dp),
                    modifier = Modifier
                        .background(cCustomColor)
                        .padding(25.dp)
                ) {
                    Text(
                        text = "Título del horario",
                        color = customColor,
                        style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 20.sp)
                    )

                    TextField(
                        value = commentValue.value,
                        onValueChange = { commentValue.value = it },
                        label = { Text("Ingrese") },
                        modifier = Modifier.fillMaxWidth(),
                        colors = customTextFieldColors
                    )

                    Row(
                        horizontalArrangement = Arrangement.spacedBy(25.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 40.dp)
                    ) {
                        Spacer(modifier = Modifier.weight(1f))

                        Button(
                            onClick = { navController.popBackStack() },
                            colors = transparentButtonColors
                        ) {
                            Text(
                                text = "Cancelar",
                                color = Color.White
                            )
                        }

                        Button(
                            onClick = { navController.navigate(Pantallas.Horario_formulario2Conexion.name) },
                            colors = transparentButtonColors
                        ) {
                            Text(
                                text = "Crear",
                                color = customColor,
                                style = TextStyle(fontWeight = FontWeight.Bold)
                            )
                        }
                    }
                }
            }
        }
    }
}
