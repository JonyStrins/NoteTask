package com.example.notetask.mapas

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.CameraPositionState
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberMarkerState

val itsur = LatLng(20.139650097620635, -101.15083476612769)

@Composable
fun MapasScreen(){

    val defaultCameraPosition = CameraPosition.fromLatLngZoom(itsur, 15f)
    val cameraPositionState = rememberCameraPositionState{
        position = defaultCameraPosition
    }
    var isMapLoaded by remember { mutableStateOf(false) }
    Box(modifier = Modifier.fillMaxSize()){
      GoogleMapView(
          modifier = Modifier.matchParentSize().fillMaxSize().height(300.dp),
          cameraPositionState = cameraPositionState,
          onMapLoaded = {
              isMapLoaded = true
          }
      )
        if(!isMapLoaded){
            AnimatedVisibility(
                modifier = Modifier
                    .matchParentSize(),
                visible = !isMapLoaded,
                enter = EnterTransition.None,
                exit = fadeOut()
            ){
                CircularProgressIndicator(
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.background)
                        .wrapContentSize()
                )
            }
        }
    }
}

@Composable
fun GoogleMapView(
    modifier: Modifier=Modifier,
    cameraPositionState: CameraPositionState = rememberCameraPositionState(),
    onMapLoaded: () -> Unit = {},
    content: @Composable () -> Unit = {}
){
    val itsurState = rememberMarkerState(position = itsur)
    GoogleMap (
        modifier = Modifier,
        cameraPositionState = cameraPositionState,
        onMapLoaded = onMapLoaded
    ){
        Marker(
            state = itsurState
        )
        content()
    }
}