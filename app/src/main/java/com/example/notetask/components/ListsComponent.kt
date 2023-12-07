package com.example.notetask.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.notetask.models.TareaEntity

@Preview
@Composable
fun ListItemComponent(get: TareaEntity, navController: NavHostController) {
    val (checkedState, onStateChange) = remember { mutableStateOf(false) }
    ListItem(
        modifier = Modifier.clip(RoundedCornerShape(10.dp)).padding(10.dp),
        headlineContent = { Text("Titulo de la tarea") },
        supportingContent = { Text("Contenido de la tarea") },
        leadingContent = {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = checkedState,
                    onCheckedChange = null
                )
                Spacer(modifier = Modifier.width(8.dp))
            }
        },
    )
}