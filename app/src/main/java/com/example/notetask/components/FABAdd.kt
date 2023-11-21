package com.example.notetask.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.notetask.repository.NotaRepository

@Composable
fun FABAdd(navController: NavHostController) {
    FloatingActionButton(
        onClick = { navController.navigate("agregarNota") },
    ) {
        Icon(Icons.Filled.Add, "Floating action button.")
    }
}

@Composable
fun FABAddExtend() {
    ExtendedFloatingActionButton(
        onClick = {  },
        icon = { Icon(imageVector = Icons.Filled.Add, contentDescription = null) },
        text = { Text(text = "Agregar Tarea")},
    )
}

@ExperimentalMaterial3Api
@Composable
fun SelectedFAB(selectedFAB: String, navController: NavHostController, repository: NotaRepository){
    if (selectedFAB.equals("Notas")){
        Scaffold(
            floatingActionButton = { FABAdd(navController) }
        ){ padding ->
            LazyVerticalGrid(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                contentPadding = PaddingValues(4.dp),
                columns = GridCells.Adaptive(150.dp)
            ) {
                items(repository.getNotas().size) {
                    CardsNotasItem(repository.getNotas().get(it), navController)
                }
            }
        }
    } else {
        Scaffold(
            floatingActionButton = { FABAddExtend() }
        ){ padding ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                contentPadding = PaddingValues(16.dp)
            ) {
                items(20) {
                    ListItemComponent()
                    Spacer(modifier = Modifier.height(7.dp))
                }
            }
        }
    }
}