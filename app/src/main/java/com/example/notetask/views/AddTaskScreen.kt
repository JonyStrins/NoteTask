@file:Suppress("DEPRECATION")
package com.example.notetask.views
import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.notetask.components.BottomAppComponentAdd
import com.example.notetask.components.BottomAppComponentEdit
import com.example.notetask.components.BottomAppTaskComponentAdd
import com.example.notetask.components.BottomAppTaskComponentEdit
import com.example.notetask.components.Carousel
import com.example.notetask.components.TopBarComponent
import com.example.notetask.components.TopBarComponentEdit
import com.example.notetask.components.TopBarTaskComponent
import com.example.notetask.components.TopBarTaskComponentEdit
import com.example.notetask.models.TareaEntity
import com.example.notetask.repository.MultimediaRepository
import com.example.notetask.repository.TareaRepository
import com.example.notetask.viewmodels.MultimediaViewModel
import com.example.notetask.viewmodels.TareaViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.Calendar
import java.util.Date
import java.util.Locale


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
@Preview
fun AddTask(
    navController: NavController,
    viewModel: TareaViewModel,
    multiRepository: MultimediaRepository
) {
    val multiViewModel = MultimediaViewModel(multiRepository, 0)
    var titulo by remember { mutableStateOf("") }
    var contenido by remember { mutableStateOf("") }
    var fechaActual = "${LocalDateTime.now().dayOfMonth}/${LocalDateTime.now().month.value}/${LocalDateTime.now().year}"
    val openDialog = remember { mutableStateOf(false) }
    val selectedDate = remember { mutableStateOf<Date?>(null) }
    val snackState = remember { SnackbarHostState() }
    val snackScope = rememberCoroutineScope()

    //crear la entity
    var entity = TareaEntity(
        id = 0,
        titulo = titulo,
        contenido = contenido,
        estatus = null,
        fecha = fechaActual,
        fechaModi = null,
        fechaCum = null,
        tipo = 1
    )
    Scaffold (
        topBar = {
                 TopBarTaskComponent("agregar Tarea", navController )
        },
        bottomBar = {
            BottomAppTaskComponentAdd(
                navController,
                viewModel,
                multiViewModel,
                entity
            )
        }
    ){ padding ->
        AddTaskContent(
            titulo = titulo,
            onTituloChange = { titulo = it },
            contenido = contenido,
            onContenidoChange = { contenido = it },
            openDialog = openDialog,
            selectedDate = selectedDate,
            snackState = snackState,
            snackScope = snackScope
        )
    }
}

@Composable
private fun AddTaskContent(
    titulo: String,
    onTituloChange: (String) -> Unit,
    contenido: String,
    onContenidoChange: (String) -> Unit,
    openDialog: MutableState<Boolean>,
    selectedDate: MutableState<Date?>,
    snackState: SnackbarHostState,
    snackScope: CoroutineScope
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        TextField(
            value = titulo,
            onValueChange = onTituloChange,
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Titulo") },
            placeholder = { Text("Titulo") }
        )
        AddTaskDatePicker(
            openDialog = openDialog,
            selectedDate = selectedDate,
            snackState = snackState,
            snackScope = snackScope
        )
        Spacer(modifier = Modifier.height(7.dp))
        TextField(
            value = contenido,
            onValueChange = onContenidoChange,
            modifier = Modifier.fillMaxSize(),
            label = { Text("Descripcion") },
            placeholder = { Text("Descripcion") }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AddTaskDatePicker(
    openDialog: MutableState<Boolean>,
    selectedDate: MutableState<Date?>,
    snackState: SnackbarHostState,
    snackScope: CoroutineScope
) {

    var calendar = Calendar.getInstance()
    calendar.set(LocalDate.now().year, LocalDate.now().monthValue-1, LocalDate.now().dayOfMonth)

    val datePickerState = rememberDatePickerState(initialSelectedDateMillis = calendar.timeInMillis)

    var selectedDate by remember {
        mutableLongStateOf(calendar.timeInMillis)
    }

    var formato = SimpleDateFormat("dd/MM/yyyy", Locale.ROOT)

    Button(
        onClick = { openDialog.value = true },
        modifier = Modifier
            .padding(8.dp)
    ) {
        Text("Seleccionar fecha")
    }
    Text(
        text = formato.format(Date(selectedDate)),
        modifier = Modifier
            .padding(8.dp)
    )
    if (openDialog.value) {
        DatePickerDialog(
            onDismissRequest = {
                openDialog.value = false
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        selectedDate =datePickerState.selectedDateMillis!!.plus(86400000)
                        openDialog.value = false
                        snackScope.launch {
                            snackState.showSnackbar(
                                "Selected date: ${formato.format(Date(selectedDate))}"
                            )
                        }
                    },
                    enabled = datePickerState.selectedDateMillis != null
                ) {
                    Text("OK")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        openDialog.value = false
                    }
                ) {
                    Text("Cancel")
                }
            }
        ) {
            DatePicker(state = datePickerState)
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun EditTaskScreen(
    id: Int,
    repository: TareaRepository,
    navController: NavController,
    viewModel: TareaViewModel,
    multiRepository: MultimediaRepository,
){
    var task = repository.getByID(id)
    val multiViewModel = MultimediaViewModel(multiRepository, id)
    var titulo by remember { mutableStateOf(task.titulo) }
    var contenido by remember { mutableStateOf(task.contenido) }
    var fechaActual = "${LocalDateTime.now().dayOfMonth}/${LocalDateTime.now().month.value}/${LocalDateTime.now().year}"
    val openDialog = remember { mutableStateOf(false) }
    val selectedDate = remember { mutableStateOf<Date?>(null) }
    val snackState = remember { SnackbarHostState() }
    val snackScope = rememberCoroutineScope()

    var entity = TareaEntity(
        id = 0,
        titulo = titulo,
        contenido = contenido,
        estatus = null,
        fecha = "00/00/00",
        fechaModi = fechaActual,
        fechaCum = null,
        tipo = 1
    )
    Scaffold(
        topBar = {
                 TopBarTaskComponentEdit("Editar tarea", navController, viewModel, entity)
        },
        bottomBar = {
            BottomAppTaskComponentEdit(navController, viewModel, multiViewModel, entity)
        }
    ) {padding ->
        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            if (multiViewModel.obtenerMultimediaPorNota(entity.id).isNotEmpty()){
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .padding(2.dp, 5.dp)
                ) {
                    Carousel(multiViewModel.obtenerMultimediaPorNota(entity.id))
                }
            }
            TextField(
                value = titulo.toString(),
                onValueChange = { titulo = it },
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Titulo") },
                placeholder = { Text("Titulo") }
            )
            AddTaskDatePicker(
                openDialog = openDialog,
                selectedDate = selectedDate,
                snackState = snackState,
                snackScope = snackScope
            )
            Spacer(modifier = Modifier.height(7.dp))
            TextField(
                value = contenido.toString(),
                onValueChange = { contenido = it },
                modifier = Modifier.fillMaxSize(),
                label = { Text("Descripcion") },
                placeholder = { Text("Descripcion") }
            )
        }
    }
}