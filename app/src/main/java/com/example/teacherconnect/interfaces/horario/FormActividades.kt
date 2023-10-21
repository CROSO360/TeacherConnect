package com.example.teacherconnect.interfaces.horario


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.concurrent.TimeUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.teacherconnect.LocalBackgroundColor
import com.example.teacherconnect.LocalBackgroundGradient
import com.example.teacherconnect.LocalBorderColor
import com.example.teacherconnect.LocalIsDarkMode
import com.example.teacherconnect.LocalTextColor
import com.example.teacherconnect.R
import com.example.teacherconnect.firebase.Actividades
import com.example.teacherconnect.interfaces.login.InputField
import java.util.Calendar
import com.google.firebase.auth.FirebaseAuth

@Composable
fun FondoGestion(
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
@Composable
fun FormActividadesScreen(navController: NavController) {
    val todasLasHoras = listOf("07:00","08:00", "09:00", "10:00", "11:00", "12:00",
        "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00")
    val dias = listOf("Lunes", "Martes", "Miércoles", "Jueves", "Viernes")
    val horarioviewmodel = viewModel<HorarioViewModel>()
    val horarioId by horarioviewmodel.horarioId.observeAsState(initial = "")
    val diaSeleccionado = remember { mutableStateOf("") }
    val nombre = rememberSaveable { mutableStateOf("") }
    val curso = rememberSaveable { mutableStateOf("") }
    val horasOcupadas by horarioviewmodel.horasOcupadas.observeAsState(initial = listOf())
    val horasDisponibles = todasLasHoras.filter { it !in horasOcupadas }
    val horasSeleccionadas = remember { mutableStateListOf<String>() }
    val auth = FirebaseAuth.getInstance()
    val textColor = LocalTextColor.current
    val isDarkMode = LocalIsDarkMode.current
    val backgroundGradient = LocalBackgroundGradient.current
    val backgroundColor = LocalBackgroundColor.current
    val borderColor = LocalBorderColor.current

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
        LaunchedEffect(key1 = horarioId) {
            horarioviewmodel.obtenerHorarioId()
        }

        Column (
            modifier = Modifier
                .fillMaxSize() ,
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ){
            InputField(valueState = nombre, labelId = "Nombre de la actividad", labelColor = Color.White, keyboardType = KeyboardType.Text)
            InputField(valueState = curso, labelId = "Curso", labelColor = Color.White, keyboardType = KeyboardType.Text)

            Text(modifier = Modifier.padding(top = 20.dp), text = "Seleccione el día:", fontSize = 18.sp, color = textColor.value)
            LazyRow(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(top = 16.dp)
            ) {
                items(dias) { dia ->
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .background(if (diaSeleccionado.value == dia) Color.Gray else Color.Transparent)
                            .padding(8.dp)
                            .clickable {
                                diaSeleccionado.value = dia
                                horarioviewmodel.obtenerHorasOcupadasParaDia(horarioId, dia)
                                horasSeleccionadas.clear()
                            }
                    ) {
                        Text(text = dia, color = textColor.value)
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            if (diaSeleccionado.value.isNotEmpty()) {
                Text(text = "Seleccione las horas:", fontSize = 18.sp, color = textColor.value)
                Text(text = "Máximo 3 horas seguidas", fontSize = 18.sp, color = textColor.value)
                LazyRow(
                    Modifier.padding(top = 16.dp, end = 20.dp, start = 20.dp), horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    items(horasDisponibles) { hour ->
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier
                                .background(if (horasSeleccionadas.contains(hour)) Color.Gray else Color.Transparent)
                                .padding(8.dp)
                                .clickable {
                                    if (horasSeleccionadas.contains(hour)) {
                                        horasSeleccionadas.remove(hour)
                                    } else {
                                        if (horasSeleccionadas.size < 3 && areHoursConsecutive(hour, horasSeleccionadas)
                                            && horarioviewmodel.isHourSelectionValid(hour, horasSeleccionadas, todasLasHoras)) {
                                            horasSeleccionadas.add(hour)
                                        }
                                    }
                                }
                        ) {
                            Text(text = hour, color = textColor.value)
                        }
                    }
                }
            }

            Button(onClick = {
                val actividad = Actividades(
                    horarioId = horarioId,
                    dia = diaSeleccionado.value,
                    horaEntrada = horasSeleccionadas.minOrNull() ?: "",
                    horaSalida = horasSeleccionadas.maxOrNull()?.let { sumarUnaHora(it) } ?: "",
                    nombre = nombre.value,
                    aula = curso.value
                )
                horarioviewmodel.crearActividad(
                    actividad,
                    onSuccess = {
                        nombre.value = ""
                        curso.value = ""
                        horasSeleccionadas.clear()
                        horarioviewmodel.obtenerHorarioId()
                        horarioviewmodel.obtenerHorasOcupadasParaDia(horarioId, diaSeleccionado.value)
                    },
                    onFailure = { exception ->
                    }
                )
            }, modifier = Modifier.padding(top = 16.dp)) {
                Text(text = "Guardar Actividad")
            }


        }
    }
}
fun areHoursConsecutive(hour: String, selectedHours: List<String>): Boolean {
    val format = SimpleDateFormat("HH:mm", Locale.getDefault())
    val selectedTimes = selectedHours.map { format.parse(it)?.time ?: 0L }.sorted()
    val currentHourTime = format.parse(hour)?.time ?: 0L
    if (selectedTimes.isEmpty()) return true

    // Si es consecutivo al primero o al último de la lista
    return currentHourTime == selectedTimes.first() - TimeUnit.HOURS.toMillis(1) ||
            currentHourTime == selectedTimes.last() + TimeUnit.HOURS.toMillis(1)
}
fun sumarUnaHora(hora: String): String {
    val format = SimpleDateFormat("HH:mm", Locale.getDefault())
    val date = format.parse(hora) ?: return hora
    val calendar = Calendar.getInstance().apply {
        time = date
        add(Calendar.HOUR, 1)
    }
    return format.format(calendar.time)
}

