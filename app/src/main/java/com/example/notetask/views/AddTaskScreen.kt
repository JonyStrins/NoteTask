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
import com.example.notetask.components.TopBarComponent
import com.example.notetask.components.TopBarComponentEdit
import com.example.notetask.models.TareaEntity
import com.example.notetask.repository.MultimediaRepository
import com.example.notetask.repository.TareaRepository
import com.example.notetask.viewmodels.MultimediaViewModel
import com.example.notetask.viewmodels.TareaViewModel
import java.time.LocalDateTime

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AddTask(
    navController: NavController,
    TviewModel: TareaViewModel,
    multiRepository: MultimediaRepository
){
    val multiViewModel = MultimediaViewModel(multiRepository, 0)
    var titulo by remember { mutableStateOf("") }
    var contenido by remember { mutableStateOf("") }
    var fechaActual = "${LocalDateTime.now().dayOfMonth}/${LocalDateTime.now().month.value}/${LocalDateTime.now().year}"
    var Tentity = TareaEntity(
        id = 0,
        titulo = titulo,
        contenido = contenido,
        estatus = null,
        tipo = null,
        fecha = fechaActual,
        fechaModi = null,
        fechaCum = null
    )

    Scaffold (
        topBar = {
            TopBarComponent("Agregar tarea", navController)
        }
    ){padding ->
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
                value = contenido,
                onValueChange = { contenido = it },
                modifier = Modifier.fillMaxSize(),
                label = { Text("Descripcion") },
                placeholder = { Text("Descripcion") }
            )
        }
    }
}

