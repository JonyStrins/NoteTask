package com.example.notetask

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.notetask.Permisos.GrabarAudioScreen
import com.example.notetask.Permisos.PermisoAccederMultimedia
import com.example.notetask.Permisos.fotoPermiso
import com.example.notetask.Permisos.localizacionPermiso
import com.example.notetask.Permisos.notificacionPermiso
import com.example.notetask.navigation.NavManager
import com.example.notetask.ui.theme.NoteTaskTheme
class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NoteTaskTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    //NavManager()
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q){
                        NavManager()
                        //Spacer(Modifier.size(30.dp))
                        GrabarAudioScreen()
                        fotoPermiso()
                        PermisoAccederMultimedia()
                        localizacionPermiso()
                        notificacionPermiso()
                    }
                }
            }
        }
    }
}


