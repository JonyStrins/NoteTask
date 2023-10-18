package com.example.notetask.components

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@ExperimentalMaterial3Api
@Composable
fun CardsNotasItem(nota: String) {

    val context = LocalContext.current
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        modifier = Modifier.fillMaxWidth().height(100.dp),
        onClick = {
            Toast.makeText(context, nota, Toast.LENGTH_SHORT).show()
        }
    ){
        Column {
            Text(
                text = nota,
                modifier = Modifier.padding(16.dp, 16.dp,0.dp,2.dp),
                textAlign = TextAlign.Center,
                fontSize = 20.sp
            )
            Text(
                text = "Description",
                modifier = Modifier.padding(16.dp, 2.dp),
                textAlign = TextAlign.Center,
                color = Color.Gray,
                fontSize = 15.sp
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            IconButton(
                onClick = {  },
                colors = IconButtonColors(
                    containerColor = Color.Transparent,
                    contentColor = Color.Red,
                    disabledContainerColor = Color.Red,
                    disabledContentColor = Color.White
                )
            ){
                Icon(Icons.Filled.Delete, contentDescription = null)
            }
        }
    }
}