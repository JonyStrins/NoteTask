package com.example.notetask.components

import android.media.ImageReader
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Scale
import com.example.notetask.models.MultimediaEntity
import com.example.notetask.models.NotaEntity
import com.example.notetask.repository.MultimediaRepository

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Carousel(repository: MultimediaRepository, notaID: Int){

    // Trabajar Con el ViewModel y sacar to do para no iterar imagenes

    LazyRow(
        modifier = Modifier.fillMaxWidth()
            .height(200.dp)
            .padding(2.dp, 5.dp),
        contentPadding = PaddingValues(4.dp)
    ) {
        items(repository.getMultimediaNota(notaID).size) {
            Card (
                shape = RoundedCornerShape(10.dp),
                onClick = {

                }
            ){
                AsyncImage(
                    model = repository.getMultimediaNota(notaID).get(it).uri,
                    contentDescription = null,
                    modifier = Modifier.width(150.dp).height(200.dp),
                    contentScale = ContentScale.Crop
                )
            }
        }
    }
}