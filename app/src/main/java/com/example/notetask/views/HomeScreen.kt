package com.example.notetask.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.notetask.repository.NotaRepository
import com.example.notetask.components.SelectedFAB
import com.example.notetask.repository.TareaRepository

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavHostController, repository: NotaRepository, Trepository: TareaRepository) {
    var selectedIndex by remember { mutableIntStateOf(0) }
    val options = listOf("Notas", "Tareas")

    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Text(
                options[selectedIndex],
                fontWeight = FontWeight.Bold,
                fontSize = 50.sp,
                modifier = Modifier.padding(10.dp, 0.dp, 0.dp,0.dp),
                textAlign = TextAlign.Center
            )
            SingleChoiceSegmentedButtonRow(
                modifier = Modifier.padding(0.dp,0.dp,10.dp,0.dp),
            ) {
                options.forEachIndexed{
                        index, label ->
                    SegmentedButton(
                        shape = SegmentedButtonDefaults.itemShape(index = index, count = options.size),
                        onClick = {selectedIndex = index},
                        selected = index == selectedIndex,
                    ){
                        Text(label)
                    }
                }
            }
        }
        Column {
            SelectedFAB(options.get(selectedIndex), navController, repository, Trepository )
        }
    }
}

