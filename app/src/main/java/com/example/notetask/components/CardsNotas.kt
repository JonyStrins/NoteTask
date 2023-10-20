package com.example.notetask.components

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.notetask.models.Nota
import com.example.notetask.models.NotaEntity


@ExperimentalMaterial3Api
@Composable
fun CardsNotasItem(nota: NotaEntity, navController: NavController) {

    val context = LocalContext.current

    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 5.dp
        ),
        modifier = Modifier
            .width(150.dp)
            .height(200.dp)
            .padding(5.dp),
        onClick = {
            navController.navigate("agregarNota/${nota.id}")
        }
    ){
        Column {
            Text(
                text = nota.titulo.toString(),
                modifier = Modifier.padding(16.dp, 16.dp,0.dp,2.dp),
                textAlign = TextAlign.Center,
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = nota.descripcion.toString(),
                modifier = Modifier.padding(16.dp, 2.dp),
                textAlign = TextAlign.Left,
                fontSize = 15.sp
            )
        }
    }
}