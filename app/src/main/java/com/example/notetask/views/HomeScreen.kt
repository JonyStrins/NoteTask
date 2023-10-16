package com.example.notetask.views

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.notetask.components.FABAdd
import com.example.notetask.components.FABAddExtend
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

@Preview(showBackground = true)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(){
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
            if (options.get(selectedIndex).equals("Notas")){
                Scaffold(
                    floatingActionButton = { FABAdd() }
                ){
                        padding ->
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(padding),
                        contentPadding = PaddingValues(16.dp)
                    ) {
                        items(50) {
                            ListItem(
                                headlineContent = { Text(text = "Nota ${it+1}") },
                                leadingContent = {
                                    Icon(imageVector = Icons.Default.Face, contentDescription = null)
                                }
                            )
                        }
                    }
                }
            } else {
                Scaffold(
                    floatingActionButton = { FABAddExtend() }
                ){
                        padding ->
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(padding),
                        contentPadding = PaddingValues(16.dp)
                    ) {
                        items(50) {
                            ListItem(
                                headlineContent = { Text(text = "Tarea ${it+1}") },
                                leadingContent = {
                                    Icon(imageVector = Icons.Default.Face, contentDescription = null)
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}

