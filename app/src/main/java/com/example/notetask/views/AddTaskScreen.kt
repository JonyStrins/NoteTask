@file:Suppress("DEPRECATION")
package com.example.notetask.views
import android.Manifest
import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.annotation.RequiresPermission
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.notetask.alarmanager.AlarmItem
import com.example.notetask.alarmanager.AlarmScheduler
import com.example.notetask.alarmanager.AlarmSchedulerImpl
import com.example.notetask.components.BottomAppTaskComponentAdd
import com.example.notetask.components.BottomAppTaskComponentEdit
import com.example.notetask.components.Carousel
import com.example.notetask.components.TopBarTaskComponent
import com.example.notetask.components.TopBarTaskComponentEdit
import com.example.notetask.location.PermissionBox
import com.example.notetask.mapasosmandroidcompose.OSMComposeMapa
import com.example.notetask.models.TareaEntity
import com.example.notetask.repository.MultimediaRepository
import com.example.notetask.repository.TareaRepository
import com.example.notetask.states.FechaState
import com.example.notetask.viewmodels.MultimediaViewModel
import com.example.notetask.viewmodels.TareaViewModel
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.tasks.CancellationTokenSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.Calendar
import java.util.Date
import java.util.Locale


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
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
    var secondText by remember {
        mutableStateOf("")
    }
    val alarmScheduler: AlarmScheduler = AlarmSchedulerImpl(LocalContext.current)
    var alarmItem: AlarmItem? = null
    var calendar = Calendar.getInstance()
    calendar.set(LocalDate.now().year, LocalDate.now().monthValue-1, LocalDate.now().dayOfMonth)
    var fechaState = remember { mutableStateOf(FechaState()) }



    //crear la entity
    var entity = TareaEntity(
        id = 0,
        titulo = titulo,
        contenido = contenido,
        estatus = null,
        fecha = fechaActual,
        fechaModi = null,
        fechaCum = fechaState.value.fecha,
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
    ){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .verticalScroll(rememberScrollState())
        ) {
            TextField(
                value = titulo,
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
            OutlinedTextField(value = secondText, onValueChange = {
                secondText = it
            },
                label = {
                    Text(text = "Delay Second")
                }
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(onClick = {
                    if(secondText != ""){
                        alarmItem = AlarmItem(
                            time = LocalDateTime.now().plusSeconds(
                                secondText.toLong()
                            ),
                            title = titulo,
                            message = contenido
                        )
                        alarmItem?.let(alarmScheduler::schedule)
                        secondText = ""
                    }
                }) {
                    Text(text = "Schedule")
                }
                Spacer(modifier = Modifier.width(8.dp))
                Button(onClick = {
                    alarmItem?.let(alarmScheduler::cancel)
                }) {
                    Text(text = "Cancel")
                }
                Spacer(modifier = Modifier.width(8.dp))
            }
            Spacer(modifier = Modifier.height(7.dp))
            CurrentLocationScreen()
            Spacer(modifier = Modifier.width(8.dp))
            TextField(
                value = contenido,
                onValueChange = { contenido = it },
                modifier = Modifier.fillMaxSize(),
                label = { Text("Descripcion") },
                placeholder = { Text("Descripcion") }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AddTaskDatePicker(
    openDialog: MutableState<Boolean>,
    selectedDate: MutableState<Date?>,
    snackState: SnackbarHostState,
    snackScope: CoroutineScope,
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
    var fecha by remember { mutableStateOf(task.fecha) }
    var fechaActual = "${LocalDateTime.now().dayOfMonth}/${LocalDateTime.now().month.value}/${LocalDateTime.now().year}"
    val openDialog = remember { mutableStateOf(false) }
    val selectedDate = remember { mutableStateOf<Date?>(null) }
    val snackState = remember { SnackbarHostState() }
    val snackScope = rememberCoroutineScope()
    var secondText by remember {
        mutableStateOf("")
    }
    val alarmScheduler: AlarmScheduler = AlarmSchedulerImpl(LocalContext.current)
    var alarmItem: AlarmItem? = null

    var entity = TareaEntity(
        id = id,
        titulo = titulo,
        contenido = contenido,
        estatus = null,
        fecha = fecha,
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

            OutlinedTextField(value = secondText, onValueChange = {
                secondText = it
            },
                label = {
                    Text(text = "Delay Second")
                }
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(onClick = {
                    if(secondText != ""){
                        alarmItem = AlarmItem(
                            time = LocalDateTime.now().plusSeconds(
                                secondText.toLong()
                            ),
                            title = titulo,
                            message = contenido
                        )
                        alarmItem?.let(alarmScheduler::schedule)
                        secondText = ""
                    }
                }) {
                    Text(text = "Schedule")
                }
                Spacer(modifier = Modifier.width(8.dp))
                Button(onClick = {
                    alarmItem?.let(alarmScheduler::cancel)
                }) {
                    Text(text = "Cancel")
                }
                Spacer(modifier = Modifier.width(8.dp))
            }
            Spacer(modifier = Modifier.height(7.dp))
            //aqui va lo del mapa
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

//aqui esta lo de CurrentLocationScreen
@SuppressLint("MissingPermission")
@Composable
fun CurrentLocationScreen() {
    val permissions = listOf(
        Manifest.permission.ACCESS_COARSE_LOCATION,
        Manifest.permission.ACCESS_FINE_LOCATION,
    )
    PermissionBox(
        permissions = permissions,
        requiredPermissions = listOf(permissions.first()),
        onGranted = {
            CurrentLocationContent(
                usePreciseLocation = it.contains(Manifest.permission.ACCESS_FINE_LOCATION),
            )
        },
    )
}
@RequiresPermission(
    anyOf = [Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION],
)
@Composable
fun CurrentLocationContent(usePreciseLocation: Boolean) {
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    var controlmapa by remember { mutableStateOf(false) }
    var longitud by remember { mutableStateOf(0.0) }
    var latitud by remember { mutableStateOf(0.0) }
    val locationClient = remember {
        LocationServices.getFusedLocationProviderClient(context)
    }
    var locationInfo by remember {
        mutableStateOf("")
    }

    Column(
        Modifier
            .fillMaxWidth()
            .animateContentSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Button(
            onClick = {
                // getting last known location is faster and minimizes battery usage
                // This information may be out of date.
                // Location may be null as previously no client has access location
                // or location turned of in device setting.
                // Please handle for null case as well as additional check can be added before using the method
                scope.launch(Dispatchers.IO) {
                    val result = locationClient.lastLocation.await()
                    locationInfo = if (result == null) {
                        "No se conoce la ultima localizacion. Try fetching the current location first"
                    } else {
                        "Posicion actual is \n" + "lat : ${result.latitude}\n" +
                                "long : ${result.longitude}\n" + "fetched at ${System.currentTimeMillis()}"
                    }
                }
            },
        ) {
            Text("Ultima Localización")
        }

        Button(
            onClick = {
                //To get more accurate or fresher device location use this method
                scope.launch(Dispatchers.IO) {
                    val priority = if (usePreciseLocation) {
                        Priority.PRIORITY_HIGH_ACCURACY
                    } else {
                        Priority.PRIORITY_BALANCED_POWER_ACCURACY
                    }
                    val result = locationClient.getCurrentLocation(
                        priority,
                        CancellationTokenSource().token,
                    ).await()
                    result?.let { fetchedLocation ->
                        locationInfo =
                            "Actual Localizacion es \n" + "lat : ${fetchedLocation.latitude}\n" +
                                    "long : ${fetchedLocation.longitude}\n" + "fetched at ${System.currentTimeMillis()}"
                        longitud = fetchedLocation.longitude
                        latitud = fetchedLocation.latitude
                    }
                    controlmapa = true
                }
            },
        ) {
            Column {
                Text(text = "Tomar localizacion actual")
            }
        }
        Text(
            text = locationInfo+"${controlmapa}",
        )

        Spacer(modifier = Modifier.height(190.dp))
        if (controlmapa){
            OSMComposeMapa(
                longitud = longitud,
                latitud = (latitud)
            )
        }

    }
}


