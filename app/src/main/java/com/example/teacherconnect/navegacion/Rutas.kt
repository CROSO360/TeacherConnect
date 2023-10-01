package com.example.teacherconnect.navegacion

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.teacherconnect.PantallaCarga
import com.example.teacherconnect.interfaces.login.LoginScreen

@Composable
fun Rutas(){
    val navController= rememberNavController()
    NavHost(navController = navController, startDestination = Pantallas.PantallaCargaConexion.name){
        composable(Pantallas.PantallaCargaConexion.name){
            PantallaCarga(navController=navController)
        }
        composable(Pantallas.LoginConexion.name){
            LoginScreen(navController=navController)
        }
    }
}