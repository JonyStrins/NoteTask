@file:Suppress("DEPRECATION")

package com.example.notetask.views

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.notetask.components.BottomAppComponentAdd
import com.example.notetask.components.BottomAppComponentEdit
import com.example.notetask.components.Carousel
import com.example.notetask.repository.NotaRepository
import com.example.notetask.components.TopBarComponent
import com.example.notetask.components.TopBarComponentEdit
import com.example.notetask.models.NotaEntity
import com.example.notetask.repository.MultimediaRepository
import com.example.notetask.viewmodels.MultimediaViewModel
import com.example.notetask.viewmodels.NotaViewModel
import java.time.LocalDateTime
import java.util.Date

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AddNote(
    navController: NavController,
    viewModel: NotaViewModel,
    multiRepository: MultimediaRepository
){

    val multiViewModel = MultimediaViewModel(multiRepository, 0)
    var titulo by remember { mutableStateOf("") }
    var descripcion by remember { mutableStateOf("") }

    var fechaActual = "${LocalDateTime.now().dayOfMonth}/${LocalDateTime.now().month.value}/${LocalDateTime.now().year}"

    var entity = NotaEntity(
        id = 0,
        titulo = titulo,
        descripcion = descripcion,
        multimedia = null,
        fecha = fechaActual
    )

    Scaffold(
        topBar = {
            TopBarComponent("Agregar Nota", navController)
        },
        bottomBar = {
            BottomAppComponentAdd(navController, viewModel, multiViewModel, entity)
        }

    ) {padding ->
        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            TextField(
                value = titulo,
                onValueChange = { titulo = it },
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Titulo") },
                placeholder = { Text("Titulo") }
            )
            Spacer(modifier = Modifier.height(7.dp))
            TextField(
                value = descripcion,
                onValueChange = { descripcion = it },
                modifier = Modifier.fillMaxSize(),
                label = { Text("Descripcion") },
                placeholder = { Text("Descripcion") }
            )
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun EditNoteScreen(
    id: Int,
    repository: NotaRepository,
    navController: NavController,
    viewModel: NotaViewModel,
    multiRepository: MultimediaRepository
){
    var note = repository.getByID(id)

    val multiViewModel = MultimediaViewModel(multiRepository, id)
    var titulo by remember { mutableStateOf(note.titulo) }
    var descripcion by remember { mutableStateOf(note.descripcion) }
    var fechaActual = "${LocalDateTime.now().dayOfMonth}/${LocalDateTime.now().month.value}/${LocalDateTime.now().year}"

    var entity = NotaEntity(
        id = id,
        titulo = titulo,
        descripcion = descripcion,
        multimedia = null,
        fecha = fechaActual
    )

    Scaffold(
        topBar = {
            TopBarComponentEdit("Editar Nota", navController, viewModel, entity)
        },
        bottomBar = {
            BottomAppComponentEdit(navController, viewModel, multiViewModel, entity)
        }
    ) {padding ->
        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            Carousel(multiRepository, entity.id)
            TextField(
                value = titulo.toString(),
                onValueChange = { titulo = it },
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Titulo") },
                placeholder = { Text("Titulo") }
            )
            Spacer(modifier = Modifier.height(7.dp))
            TextField(
                value = descripcion.toString(),
                onValueChange = { descripcion = it },
                modifier = Modifier.fillMaxSize(),
                label = { Text("Descripcion") },
                placeholder = { Text("Descripcion") }
            )
        }
    }
}