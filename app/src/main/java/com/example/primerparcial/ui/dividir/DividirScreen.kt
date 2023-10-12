package com.example.primerparcial.ui.dividir

import android.widget.Space
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
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
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.semantics.Role.Companion.Button
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
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
                        viewModel.Nombre = ""
                        viewModel.Dividido = 0
                        viewModel.Divisor = 0
                        viewModel.Cociente = 0
                        viewModel.Residuo = 0

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
                    value = viewModel.Nombre,
                    onValueChange = { viewModel.Nombre = it },
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text(text = "Nombre") },
                    singleLine = true
                )
                Spacer(modifier = Modifier.height(10.dp))

                Row {
                    OutlinedTextField(
                        value = viewModel.Dividido.toString(),
                        onValueChange = {
                            val newValue = it.toInt()
                            if (newValue != null) {
                                viewModel.Dividido = newValue
                            }
                        },
                        modifier = Modifier.weight(1f),
                        label = { Text(text = "Dividido") },
                        singleLine = true,
                        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next)
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    OutlinedTextField(
                        value = viewModel.Divisor.toString(),
                        onValueChange = {
                            val newValue = it.toInt()
                            if (newValue != null) {
                                viewModel.Divisor = newValue
                            }
                        },
                        modifier = Modifier.weight(1f),
                        label = { Text(text = "Divisor") },
                        singleLine = true,
                        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next)
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))
                Row {
                    OutlinedTextField(
                        value = viewModel.Cociente.toString(),
                        onValueChange = {
                            val newValue = it.toInt()
                            if (newValue != null) {
                                viewModel.Cociente = newValue
                            }
                        },
                        modifier = Modifier.weight(1f),
                        label = { Text(text = "Cociente") },
                        singleLine = true,
                        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next)
                    )
                    Spacer(modifier = Modifier.width(16.dp))

                    OutlinedTextField(
                        value = viewModel.Residuo.toString(),
                        onValueChange = {
                            val newValue = it.toInt()
                            if (newValue != null) {
                                viewModel.Residuo = newValue
                            }
                        },
                        modifier = Modifier.weight(1f),
                        label = { Text(text = "Residuo") },
                        singleLine = true,
                        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next)
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))

                val keyboardController =
                    LocalSoftwareKeyboardController.current
                OutlinedButton(onClick = {
                    keyboardController?.hide()
                    if (viewModel.validar()) {
                        viewModel.Dividido = viewModel.Cociente * viewModel.Divisor + viewModel.Residuo
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
                            .border(width = 1.dp, color = Color.Black, shape = RoundedCornerShape(10.dp))
                            .padding(13.dp)
                    ) {
                        Text(text = "Nombre: " + division.nombre)
                        Text(text = "Division: " + division.dividido.toString())
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



