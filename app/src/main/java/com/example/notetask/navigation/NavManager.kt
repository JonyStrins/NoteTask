package com.example.notetask.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.example.notetask.repository.NotaRepository
import com.example.notetask.database.NotaDataBase
import com.example.notetask.repository.MultimediaRepository
import com.example.notetask.repository.TareaRepository
import com.example.notetask.viewmodels.MultimediaViewModel
import com.example.notetask.viewmodels.NotaViewModel
import com.example.notetask.viewmodels.TareaViewModel
import com.example.notetask.views.AddNote
import com.example.notetask.views.AddTask
import com.example.notetask.views.EditNoteScreen
import com.example.notetask.views.EditTaskScreen
import com.example.notetask.views.HomeScreen

@Preview(showBackground = true)
@Composable
fun NavManager(){
    val navController = rememberNavController()
    val db = Room.databaseBuilder(LocalContext.current, NotaDataBase::class.java, "nota_db").allowMainThreadQueries().fallbackToDestructiveMigration().build()
    val dao = db.dao
    val multiDao = db.multiDao
    val repository = NotaRepository(dao)
    val multiRepository = MultimediaRepository(multiDao)
    val notasViewModel = NotaViewModel(repository)
    //prueba de lo mio
    val tdao = db.tDao
    val Trepository = TareaRepository(tdao)
    val tareasViewModel = TareaViewModel(Trepository)


    NavHost(navController = navController, startDestination = "inicio"){
        composable(route = "inicio"){
            HomeScreen(navController, repository, Trepository)
        }
        composable(route = "agregarNota"){
            AddNote(navController, viewModel = notasViewModel, multiRepository)
        }
        composable(route = "editarNota/{id}"){
            val id = it.arguments?.getString("id")?.toInt()
            if (id != null) {
                EditNoteScreen(id, repository, navController, viewModel = notasViewModel, multiRepository)
            }else{
                HomeScreen(navController, repository, Trepository)
            }
        }
        composable(route = "agregarTarea"){
            AddTask(navController, viewModel = tareasViewModel , multiRepository )
        }
        composable(route = "editarTarea/{id}"){
            val id = it.arguments?.getString("id")?.toInt()
            if(id != null){
                EditTaskScreen( id, Trepository, navController, viewModel = tareasViewModel, multiRepository)
            }else{
                HomeScreen(navController, repository, Trepository)
            }
        }
    }
}