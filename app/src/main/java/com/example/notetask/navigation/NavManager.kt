package com.example.notetask.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.example.notetask.repository.NotaRepository
import com.example.notetask.database.NotaDataBase
import com.example.notetask.viewmodels.NotaViewModel
import com.example.notetask.views.AddNote
import com.example.notetask.views.EditNoteScreen
import com.example.notetask.views.HomeScreen

@Preview(showBackground = true)
@Composable
fun NavManager(){
    val navController = rememberNavController()
    val db = Room.databaseBuilder(LocalContext.current, NotaDataBase::class.java, "nota_db").allowMainThreadQueries().build()
    val dao = db.dao
    val repository = NotaRepository(dao)
    val notasViewModel = NotaViewModel(repository)

    NavHost(navController = navController, startDestination = "inicio"){
        composable(route = "inicio"){
            HomeScreen(navController, repository)
        }
        composable(route = "agregarNota"){
            AddNote(navController, viewModel = notasViewModel)
        }
        composable(route = "agregarNota/{id}"){
            val id = it.arguments?.getString("id")?.toInt()
            if (id != null) {
                EditNoteScreen(id, repository, navController, viewModel = notasViewModel)
            }
        }
    }
}