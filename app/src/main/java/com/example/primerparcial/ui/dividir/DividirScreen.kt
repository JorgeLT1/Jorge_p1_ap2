package com.example.primerparcial.ui.dividir

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.flow.collectLatest


@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun DividirScreen(viewModel: DividirViewModel = hiltViewModel()) {
    val dividir by viewModel.dividir.collectAsState()
    var isSaveButtonClicked by remember { mutableStateOf(false) }

    val snackbarHostState = remember { SnackbarHostState() }
    LaunchedEffect(Unit) {
        viewModel.isMessageShownFlow.collectLatest {
            if (it) {
                snackbarHostState.showSnackbar(
                    message = "Division guardada.",
                    duration = SnackbarDuration.Short
                )
            }
        }

    }
    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text(text = "Aprende a dividir") },
                actions = {
                    IconButton(onClick = {
                        viewModel.nombre = ""
                        viewModel.dividendo = 0
                        viewModel.divisor = 0
                        viewModel.cociente = 0
                        viewModel.residuo = 0

                    }) {
                        Icon(
                            imageVector = Icons.Default.Refresh,
                            contentDescription = "Refresh"

                        )

                    }
                }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .padding(8.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {

                OutlinedTextField(
                    value = viewModel.nombre,
                    onValueChange = { viewModel.nombre = it },
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text(text = "Nombre") },
                    singleLine = true
                )
                if (viewModel.nombreVacio == false)
                {
                    Text(text = "El nombre es requerido.", color = Color.Red, fontSize = 12.sp)
                }

                Spacer(modifier = Modifier.height(10.dp))

                Row {
                    Column(modifier = Modifier.padding(5.dp).weight(1f)){
                        OutlinedTextField(
                            value = viewModel.dividendo.toString(),
                            onValueChange = {
                                val newValue = it.toInt()
                                if (newValue != null) {
                                    viewModel.dividendo = newValue
                                }
                            },
                            label = { Text(text = "Dividendo") },
                            singleLine = true,
                            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next, keyboardType = KeyboardType.Number)
                        )
                        if (viewModel.vacioDividido == false)
                        {
                            Text(text = "Dividendo requerido.", color = Color.Red, fontSize = 12.sp)
                        }

                    }

                    Spacer(modifier = Modifier.width(16.dp))

                    Column(modifier = Modifier.padding(5.dp).weight(1f)){
                        OutlinedTextField(
                            value = viewModel.divisor.toString(),
                            onValueChange = {
                                val newValue = it.toInt()
                                if (newValue != null) {
                                    viewModel.divisor = newValue
                                }
                            },
                            label = { Text(text = "Divisor") },
                            singleLine = true,
                            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next, keyboardType = KeyboardType.Number)
                        )
                        if (viewModel.vacioDivisor == false)
                        {
                            Text(text = "Divisor requerido.", color = Color.Red, fontSize = 12.sp)
                        }
                        if (viewModel.validarDivisor == false)
                        {
                            Text(text = "Divisor incorrecto.", color = Color.Red, fontSize = 12.sp)
                        }
                    }


                }

                Spacer(modifier = Modifier.height(10.dp))
                Row {

                    Column(modifier = Modifier.padding(5.dp).weight(1f)) {

                        OutlinedTextField(
                            value = viewModel.cociente.toString(),
                            onValueChange = {
                                val newValue = it.toInt()
                                if (newValue != null) {
                                    viewModel.cociente = newValue
                                }
                            },
                            label = { Text(text = "Cociente") },
                            singleLine = true,
                            keyboardOptions = KeyboardOptions.Default.copy(
                                imeAction = ImeAction.Next,
                                keyboardType = KeyboardType.Number
                            )
                        )
                        if (viewModel.vacioCociente == false)
                        {
                            Text(text = "Cociente requerido.", color = Color.Red, fontSize = 12.sp)
                        }
                        if (viewModel.validarCociente == false)
                        {
                            Text(text = "Cociente invalido.", color = Color.Red, fontSize = 12.sp)
                        }
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                    Column(modifier = Modifier.padding(5.dp).weight(1f)) {
                        OutlinedTextField(
                            value = viewModel.residuo.toString(),
                            onValueChange = {
                                val newValue = it.toInt()
                                if (newValue != null) {
                                    viewModel.residuo = newValue
                                }
                            },
                            label = { Text(text = "Residuo") },
                            singleLine = true,
                            keyboardOptions = KeyboardOptions.Default.copy(
                                imeAction = ImeAction.Next,
                                keyboardType = KeyboardType.Number
                            )
                        )
                        if (viewModel.vacioResiduo == false)
                        {
                            Text(text = "Residuo requerido.", color = Color.Red, fontSize = 12.sp)
                        }
                        if (viewModel.validarResiduo == false)
                        {
                            Text(text = "Residuo invalido.", color = Color.Red, fontSize = 12.sp)
                        }
                    }
                }
                Spacer(modifier = Modifier.height(10.dp))

                val keyboardController =
                    LocalSoftwareKeyboardController.current
                OutlinedButton(onClick = {
                    keyboardController?.hide()
                    if (viewModel.validar()) {
                        viewModel.save()
                    }
                }, modifier = Modifier.fillMaxWidth())
                {
                    Icon(imageVector = Icons.Default.CheckCircle, contentDescription = "Guardar")
                    Text(text = "Guardar")
                }
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                    .border(width = 0.5.dp, color = Color.Black, shape = RoundedCornerShape(5.dp))
                    .padding(10.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Historial de resultados",
                    style = TextStyle(fontSize = 19.sp),
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            LazyColumn(modifier = Modifier.fillMaxWidth()) {
                items(dividir) { division ->
                    Column(
                        modifier = Modifier
                            .padding(13.dp)
                            .fillMaxWidth()
                            .background(Color(0xFFB3E5FC))
                            .border(
                                width = 1.dp,
                                color = Color.Black,
                                shape = RoundedCornerShape(10.dp)
                            )
                            .padding(13.dp)
                    ) {
                        Text(text = "Nombre: " + division.nombre)
                        Text(text = "Division: " + division.dividendo.toString())
                        Text(text = "Divisor: " + division.divisor.toString())
                        Text(text = "Cociente: " + division.cociente.toString())
                        Text(text = "Residuo: "+ division.residuo.toString())

                        Button(
                            onClick = { viewModel.delete(division) },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(13.dp),
                            colors = ButtonDefaults.buttonColors(
                                contentColor = Color.White
                            )
                        ) {
                            Text(text = "Eliminar")
                        }
                    }
                }
            }



        }
        }
    }



