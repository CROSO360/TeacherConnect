package com.example.teacherconnect.interfaces.canal

import androidx.navigation.NavController
import com.example.teacherconnect.R
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.teacherconnect.ui.theme.TeacherConnectTheme




@Composable
fun Crear_CanalScreen(navController: NavController){
        TeacherConnectTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Box(modifier = Modifier.fillMaxSize()) {

                        Image(
                            painter = painterResource(id = R.drawable.fondocrear),
                            contentDescription = "Background Image",
                            contentScale = ContentScale.FillBounds,
                            modifier = Modifier.fillMaxSize()
                        )
                        Box(
                            modifier = Modifier
                                .align(Alignment.Center)
                                .clip(RoundedCornerShape(20.dp))
                                .background(Color.Gray.copy(alpha = 0.3f))
                                .padding(16.dp)
                                .width(300.dp)
                                .height(250.dp)
                                .fillMaxSize()
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.emoji1),
                                contentDescription = "Icon",
                                modifier = Modifier
                                    .size(66.dp)
                                    .align(Alignment.TopStart)
                                    .padding(4.dp)
                            )

                            Column(
                                modifier = Modifier.fillMaxSize().padding(start = 70.dp),
                                horizontalAlignment = Alignment.Start
                            ) {
                                Text(
                                    text = "Nombre Del Canal",
                                    style = MaterialTheme.typography.headlineMedium.copy(fontSize = MaterialTheme.typography.headlineMedium.fontSize / 2),
                                    modifier = Modifier.padding(4.dp)
                                )

                                Spacer(modifier = Modifier.height(8.dp))

                                var message by remember { mutableStateOf(TextFieldValue()) }
                                BasicTextField(
                                    value = message,
                                    onValueChange = { message = it },
                                    modifier = Modifier
                                        .width(200.dp)
                                        .height(40.dp) //
                                        .clip(RoundedCornerShape(20.dp)) //
                                        .border(BorderStroke(1.dp, Color.White), shape = RoundedCornerShape(20.dp))
                                        .background(Color.White, shape = RoundedCornerShape(20.dp))
                                        .shadow(4.dp, shape = RoundedCornerShape(20.dp)) //
                                        .padding(8.dp)
                                )

                                Spacer(modifier = Modifier.weight(1f))
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.Start
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .background(Color.Transparent)
                                            .clickable {  }
                                            .padding(horizontal = 22.dp, vertical = 8.dp),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Text(text = "Cancelar", color = Color.Black, fontSize = 14.sp)
                                    }
                                    Box(
                                        modifier = Modifier
                                            .background(Color.Transparent)
                                            .clickable { }
                                            .padding(8.dp),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Text(text = "Aceptar", color = Color.Black, fontSize = 14.sp)
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }






















