package com.example.notetask.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.example.notetask.models.Nota
import com.example.notetask.viewmodels.NotaViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarComponent(
    agregacion: String,
    navController: NavController,
    viewModel: NotaViewModel,
    entity: Nota?
){
    TopAppBar(
        title = { Text(agregacion) },
        navigationIcon = {
            IconButton(onClick = {
                navController.popBackStack()
            }){
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null)
            }
        },
        actions = {
            IconButton(onClick = {
                if (entity != null) {
                    viewModel.guardarNota(entity)
                    navController.popBackStack()
                }
            }
            ){
                Icon(Icons.Default.Check, contentDescription = null, tint = Color.Green)
            }
        }
    )
}