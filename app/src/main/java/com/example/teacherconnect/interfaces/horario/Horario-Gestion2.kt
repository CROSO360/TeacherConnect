package com.example.teacherconnect.interfaces.horario


import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.unit.dp
import com.example.teacherconnect.R
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.input.KeyboardType

import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HorarioFormulario2Screen(navController: NavController) {
    Image(
        painter = painterResource(id = R.drawable.fondo5),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier.fillMaxSize()
    )
    val customColor = Color(0xFF33FFCC)
    val transparentButtonColors = ButtonDefaults.buttonColors(Color.Gray)
    val commentValue = remember { mutableStateOf("") }
    val dayValue = remember { mutableStateOf("") }
    val timeValue = remember { mutableStateOf("") }
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
        modifier = Modifier.fillMaxSize()
            .offset(y = 48.dp),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .width(355.dp)
                .height(545.dp)
                .clip(RoundedCornerShape(20.dp))
                .background(Color.Gray),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(20.dp),
                modifier = Modifier.background(Color.Gray).padding(25.dp)
            ) {
                Text(
                    modifier = Modifier
                        .offset(y = (-30).dp),
                    text = "Ingrese por dia y hora sus actividades",
                    color = customColor,
                    style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 15.sp)
                )
                TextField(
                    value = dayValue.value,
                    onValueChange = { dayValue.value = it },
                    label = { Text("Seleccione El Día") },
                    modifier = Modifier.fillMaxWidth(),
                    colors = customTextFieldColors,
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Text
                    )
                )

                Spacer(modifier = Modifier.height(8.dp))

                TextField(
                    value = timeValue.value,
                    onValueChange = { timeValue.value = it },
                    label = { Text("Seleccione La Hora") },
                    modifier = Modifier.fillMaxWidth(),
                    colors = customTextFieldColors,
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Text
                    )
                )

                Spacer(modifier = Modifier.height(8.dp))

                TextField(
                    value = commentValue.value,
                    onValueChange = { commentValue.value = it },
                    label = { Text("Escriba Su Actividad") },
                    modifier = Modifier.fillMaxWidth(),
                    colors = customTextFieldColors,


                )
                Button(
                    modifier = Modifier
                        .offset(y = 20.dp),
                    onClick = { navController.popBackStack()},
                    colors = transparentButtonColors
                ){
                    Text(
                        text ="Cancelar",
                        color = Color.White
                    )
                }
                Button(
                    modifier = Modifier
                        .offset(y = 40.dp),
                    onClick = { },
                    colors = transparentButtonColors
                ) {
                    Text(
                        text = "Finalizar registro",
                        color = customColor,
                        style = TextStyle(fontWeight = FontWeight.Bold)
                    )
                }
            }
        }
    }
}
