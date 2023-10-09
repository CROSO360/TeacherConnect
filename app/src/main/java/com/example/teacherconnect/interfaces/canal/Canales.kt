package com.example.teacherconnect.interfaces.canal

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.teacherconnect.R
import androidx.compose.foundation.layout.Spacer
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import com.google.firebase.auth.FirebaseAuth
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.text.font.FontWeight
import com.example.teacherconnect.firebase.Canales
import com.example.teacherconnect.navegacion.Pantallas

@Composable
fun ChannelScreen(navController: NavController){
    val canalViewModel: CanalViewModel = viewModel()
    val userId = FirebaseAuth.getInstance().currentUser?.uid
    val showDialog = remember { mutableStateOf(false) }
    showDialog.value = false

    Box(
        modifier = Modifier
        .fillMaxSize()
        .background(Color.Black)
    ) {
        Image(
            painter = painterResource(id = R.drawable.fondo),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
       
        TextButton(
            onClick = {navController.navigate(Pantallas.Home_CanalConexion.name)},
            modifier = Modifier
            .align(Alignment.TopStart)
            .padding(start = 30.dp, top = 35.dp)
        ) {
            Text("< Volver", color = Color.White, fontSize = 18.sp)
        }
        LaunchedEffect(key1 = userId) {
            userId?.let {
                canalViewModel.CanalesDelUsuario(it)
            }
        }

        val canales by canalViewModel.canales.observeAsState(listOf())
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center
        ){
            Text(
                "Tus \ncanales", color = Color.White, fontSize = 45.sp,
                lineHeight = 50.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 115.dp, start = 30.dp).align(Alignment.Start)
            )
            if (canales.isEmpty()) {
                showDialog.value = true
            } else {
                ListaDeCanales(canales = canales, modifier = Modifier.weight(1f))
            }
        }
        if (showDialog.value) {
            AlertDialog(
                onDismissRequest = {
                    showDialog.value = false
                },
                title = { Text(text = "Información") },
                text = { Text("No tiene canales creados") },
                confirmButton = {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        horizontalArrangement = Arrangement.End
                    ) {
                        TextButton(onClick = {
                            showDialog.value = false
                            navController.navigate(Pantallas.Home_CanalConexion.name)
                        })  {
                            Text("Volver")
                        }
                    }
                }
            )
        }

        Image(
            painter = painterResource(id = R.drawable.icono_soporte),
            contentDescription = null,
            modifier = Modifier
            .align(Alignment.BottomEnd)
            .padding(5.dp)
        )
    }
}

@Composable
fun ListaDeCanales(canales: List<Canales>, modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier.padding(8.dp)) {
        items(canales.chunked(2)) { chunkedCanales ->
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth().padding(horizontal = 5.dp)
            ) {
                CanalItem(chunkedCanales[0], Modifier.weight(1f))
                Spacer(Modifier.width(8.dp))
                if (chunkedCanales.size > 1) {
                    CanalItem(chunkedCanales[1], Modifier.weight(1f))
                }
            }
        }
    }
}


@Composable
fun CanalItem(canal: Canales, modifier: Modifier = Modifier) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .padding(8.dp)
            .size(130.dp)
            .background(Color.Transparent, shape = MaterialTheme.shapes.medium)
    ) {
        Image(
            painter = painterResource(id = R.drawable.emoji1),
            contentDescription = null,
            modifier = Modifier.size(75.dp)
        )
        Text(text = canal.nombreCanal, color = Color.White, fontSize = 14.sp)
    }
}

