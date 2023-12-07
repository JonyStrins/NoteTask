package com.example.notetask.components

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.navigation.NavController
import com.example.notetask.R
import com.example.notetask.models.MultimediaEntity
import com.example.notetask.models.TareaEntity
import com.example.notetask.viewmodels.MultimediaViewModel
import com.example.notetask.viewmodels.TareaViewModel

@Composable
fun BottomAppTaskComponentAdd(
    navController: NavController,
    viewModel: TareaViewModel,
    viewModelMulti: MultimediaViewModel,
    entity: TareaEntity
){
    var selectedUris by remember{
        mutableStateOf<List<Uri>>(emptyList())
    }

    val multiplePhotoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickMultipleVisualMedia(),
        onResult = { uris -> selectedUris = uris }
    )
    BottomAppBar(
        actions = {
            IconButton(onClick = {
                multiplePhotoPickerLauncher.launch(
                    PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageAndVideo)
                )
            }){
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.add_photo_alternate),
                    ""
                )
            }
            IconButton(onClick = {  }){
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.camera),
                    ""
                )
            }
            IconButton(onClick = {  }){
                Icon(
                    imageVector = Icons.Default.Email,
                    ""
                )
            }
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                if (entity != null) {
                    viewModel.guardarTarea(entity)
                    navController.popBackStack()
                }
                selectedUris.forEach{
                    if (entity != null) {
                        viewModelMulti.agregarMultimedia(MultimediaEntity(0, it.toString(), 1, viewModel.obtenerUltimaTarea(), null, null, null))
                    }
                }
            }, containerColor = Color.Green){
                Icon(
                    imageVector = Icons.Default.Check,
                    "",
                    tint = Color.DarkGray
                )
            }
        }
    )
}

@Composable
fun BottomAppTaskComponentEdit(
    navController: NavController,
    viewModel: TareaViewModel,
    viewModelMulti: MultimediaViewModel,
    entity: TareaEntity
){
    var selectedUris by remember{
        mutableStateOf<List<Uri>>(emptyList())
    }

    val multiplePhotoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickMultipleVisualMedia(),
        onResult = { uris -> selectedUris = uris }
    )

    BottomAppBar(
        actions = {
            IconButton(onClick = {
                multiplePhotoPickerLauncher.launch(
                    PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageAndVideo)
                )
            }){
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.add_photo_alternate),
                    ""
                )
            }
            IconButton(onClick = {  }){
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.camera),
                    ""
                )
            }
            IconButton(onClick = {  }){
                Icon(
                    imageVector = Icons.Default.Email,
                    ""
                )
            }
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                if (entity != null) {
                    viewModel.actualizarTarea(entity)
                    navController.popBackStack()
                }
                selectedUris.forEach{
                    if (entity != null) {
                        viewModelMulti.agregarMultimedia(MultimediaEntity(0, it.toString(), 1, entity.id, null, null, null))
                    }
                }
            }, containerColor = Color.Green){
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.save),
                    "",
                    tint = Color.DarkGray
                )
            }
        }
    )
}