package com.example.notetask.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.notetask.views.AddNote
import com.example.notetask.views.HomeScreen

@Preview
@Composable
fun NavManager(){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "inicio"){
        composable(route = "inicio"){
            HomeScreen(navController)
        }
        composable(route = "agregarNota"){
            AddNote()
        }
    }
}