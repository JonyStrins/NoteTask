package com.example.notetask.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.notetask.models.TareaEntity

@Preview
@Composable
fun ListItemComponent(
    tarea: TareaEntity,
    navController: NavHostController
) {
    val (checkedState, onCheckedChange) = remember { mutableStateOf(false) }
    val backgroundColor = if (checkedState) Color.Cyan else Color.Transparent
    ListItem(
        modifier = Modifier
            .clip(RoundedCornerShape(10.dp))
            .padding(10.dp)
            .background(backgroundColor)
            .clickable{navController.navigate("editarTarea/${tarea.id}")},
        headlineContent = { tarea.titulo?.let { Text(it) } },
        supportingContent = { tarea.contenido?.let { Text(it) } },
        leadingContent = {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = checkedState,
                    onCheckedChange = { newCheckedState -> onCheckedChange(newCheckedState)},
                    modifier = Modifier
                        .background(backgroundColor)
                    )
                Spacer(modifier = Modifier.width(8.dp))
            }
        },
    )
}