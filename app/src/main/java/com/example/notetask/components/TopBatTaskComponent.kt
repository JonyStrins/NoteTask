package com.example.notetask.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.notetask.models.TareaEntity
import com.example.notetask.viewmodels.TareaViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarTaskComponent(
    agregacion: String,
    navController: NavController,
){
    TopAppBar(
        title = { Text(agregacion)},
        navigationIcon = {
            IconButton(onClick = {
                navController.popBackStack()
            }) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null)
            }
        }
    )
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarTaskComponentEdit(
    agregacion: String,
    navController: NavController,
    viewModel: TareaViewModel,
    entity: TareaEntity
){
    TopAppBar(
        title = { Text(agregacion)},
        navigationIcon = {
            IconButton(onClick = {
                navController.popBackStack()
            }){
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null)
            }
        },
        actions = {
            IconButton(onClick = {
                viewModel.eliminarTarea(entity)
                navController.popBackStack()
            }){
                Icon(Icons.Outlined.Delete, contentDescription = null)
            }
        }
    )
}