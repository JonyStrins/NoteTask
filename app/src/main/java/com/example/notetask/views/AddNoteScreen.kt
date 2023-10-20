package com.example.notetask.views

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.notetask.components.TopBarComponent
import com.example.notetask.models.Nota
import com.example.notetask.viewmodels.NotaViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddNote(navController: NavController, viewModel: NotaViewModel){
    var titulo by remember { mutableStateOf("") }
    var descripcion by remember { mutableStateOf("") }
    var state = viewModel.state

    var entity = Nota(
        titulo = titulo,
        descripcion = descripcion,
        multimedia = null,
        fecha = null
    )

    Scaffold(
        topBar = {
            TopBarComponent("Agregar Nota", navController, viewModel, entity)
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