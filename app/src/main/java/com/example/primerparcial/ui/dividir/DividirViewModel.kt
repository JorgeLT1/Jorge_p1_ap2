package com.example.primerparcial.ui.dividir

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.primerparcial.data.repository.DividirRepository
import com.example.primerparcial.data.repository.local.entities.Dividir
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DividirViewModel @Inject constructor(
    private val repository: DividirRepository
) : ViewModel() {

    var Nombre by mutableStateOf("")
    var Dividido by mutableStateOf(0)
    var Divisor by mutableStateOf(0)
    var Cociente by mutableStateOf(0)
    var Residuo by mutableStateOf(0)

    private val _isMessageShown = MutableSharedFlow<Boolean>()
    val isMessageShownFlow = _isMessageShown.asSharedFlow()
    fun setMessageShown() {
        viewModelScope.launch {
            _isMessageShown.emit(true)
        }
    }

    fun validar() : Boolean{


        return !(Nombre == "" || Dividido == 0 || Divisor == 0 || Cociente == 0 || Residuo == 0)
    }
    val dividir: StateFlow<List<Dividir>> = repository.getAll().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = emptyList()
    )

    fun save(){
        viewModelScope.launch {
            val dividir = Dividir(
                nombre = Nombre,
                dividido = Dividido,
                divisor = Divisor,
                cociente = Cociente,
                residuo = Residuo,
            )
            repository.save(dividir)
            limpiar()
        }
    }

    fun delete(dividir: Dividir) {
        viewModelScope.launch {
            repository.delete(dividir)
        }
    }



    fun limpiar()
    {
        Nombre = ""
        Dividido = 0
        Divisor = 0
        Cociente = 0
        Residuo = 0
    }


}