package com.example.primerparcial.ui.dividir

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Refresh
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
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
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
                title = { Text(text = "Division") },
                actions = {
                    IconButton(onClick = {
                        viewModel.Nombre = ""
                        viewModel.Dividido = 0.0
                        viewModel.Divisor = 0.0
                        viewModel.Cociente = 0.0
                        viewModel.Residuo = 0.0

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
                Text(
                    text = "Detalles de la division", style =
                    MaterialTheme.typography.titleMedium
                )
                OutlinedTextField(
                    value = viewModel.Nombre,
                    onValueChange = { viewModel.Nombre = it },
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text(text = "Nombre") },
                    singleLine = true
                )
                OutlinedTextField(
                    value = viewModel.Dividido.toString(),
                    onValueChange = {
                        val newValue = it.toDoubleOrNull()
                        if (newValue != null) {
                            viewModel.Dividido = newValue
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text(text = "Dividido") },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next)
                )
                OutlinedTextField(
                    value = viewModel.Divisor.toString(),
                    onValueChange = {
                        val newValue = it.toDoubleOrNull()
                        if (newValue != null) {
                            viewModel.Divisor = newValue
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text(text = "Divisor") },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next)
                )
                OutlinedTextField(
                    value = viewModel.Cociente.toString(),
                    onValueChange = {
                        val newValue = it.toDoubleOrNull()
                        if (newValue != null) {
                            viewModel.Cociente = newValue
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text(text = "Cociente") },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next)
                )
                OutlinedTextField(
                    value = viewModel.Residuo.toString(),
                    onValueChange = {
                        val newValue = it.toDoubleOrNull()
                        if (newValue != null) {
                            viewModel.Residuo = newValue
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text(text = "Residuo") },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next)
                )
                val keyboardController =
                    LocalSoftwareKeyboardController.current
                OutlinedButton(onClick = {
                    keyboardController?.hide()
                    if (viewModel.validar()) {
                        viewModel.Dividido =viewModel.Cociente * viewModel.Divisor + viewModel.Residuo
                        viewModel.save()
                    }
                }, modifier = Modifier.fillMaxWidth())
                {
                    Icon(imageVector = Icons.Default.CheckCircle, contentDescription = "Guardar")
                    Text(text = "Guardar")
                }
            }
            Text(
                text = "Lista de divisiones", style =
                MaterialTheme.typography.titleMedium
            )
            LazyColumn(modifier = Modifier.fillMaxWidth()) {
                items(dividir) { division ->
                    Text(text = division.nombre)
                    Text(text = division.dividido.toString())
                    Text(text = division.divisor.toString())
                    Text(text = division.cociente.toString())
                    Text(text = division.residuo.toString())
                }
            }



            }
        }
    }



