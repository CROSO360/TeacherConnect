package com.example.teacherconnect.navegacion

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.teacherconnect.PantallaCarga
import com.example.teacherconnect.interfaces.canal.ChannelScreen
import com.example.teacherconnect.interfaces.canal.Home_CanalScreen
import com.example.teacherconnect.interfaces.home.Home
import com.example.teacherconnect.interfaces.horario.HorarioActividadesScreen
import com.example.teacherconnect.interfaces.horario.HorarioFormulario1Screen
import com.example.teacherconnect.interfaces.horario.HorarioFormulario2Screen
import com.example.teacherconnect.interfaces.horario.HorarioHomeScreen
import com.example.teacherconnect.interfaces.horario.HorarioVerScreen
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
        composable(Pantallas.HomeConexion.name){   
             Home(navController=navController)
        }
        composable(Pantallas.Horario_homeConexion.name){
            HorarioHomeScreen(navController=navController)
        }
        composable(Pantallas.Horario_formulario1Conexion.name){
            HorarioFormulario1Screen(navController=navController)
        }
        composable(Pantallas.Horario_formulario2Conexion.name){
            HorarioFormulario2Screen(navController=navController)
        }
        composable(Pantallas.Horario_verConexion.name){
            HorarioVerScreen(navController=navController)
        }
        composable(Pantallas.Horario_actividadesConexion.name){
            HorarioActividadesScreen(navController=navController)
        }
        composable(Pantallas.Home_CanalConexion.name){
            Home_CanalScreen(navController=navController)
        }
        composable(Pantallas.TusCanalesConexion.name){
            ChannelScreen(navController=navController)
        }
    }
}