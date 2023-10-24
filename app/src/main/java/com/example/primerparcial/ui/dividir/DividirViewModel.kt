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

    var nombre by mutableStateOf("")
    var dividendo by mutableStateOf(0)
    var divisor by mutableStateOf(0)
    var cociente by mutableStateOf(0)
    var residuo by mutableStateOf(0)
    var nombreVacio by mutableStateOf(true)
    var vacioDividido by mutableStateOf(true)
    var vacioDivisor by mutableStateOf(true)
    var vacioCociente by mutableStateOf(true)
    var vacioResiduo by mutableStateOf(true)
    var validacionDividento by mutableStateOf(true)
    var validarDivisor by mutableStateOf(true)
    var validarCociente by mutableStateOf(true)
    var validarResiduo by mutableStateOf(true)



    private val _isMessageShown = MutableSharedFlow<Boolean>()
    val isMessageShownFlow = _isMessageShown.asSharedFlow()
    fun setMessageShown() {
        viewModelScope.launch {
            _isMessageShown.emit(true)
        }
    }


    fun validar() : Boolean{

        if(nombre == "")
        {
            nombreVacio = false
        }
        else
        {
            nombreVacio = true
        }

        if (dividendo <= 0)
        {
            vacioDividido = false
        }
        else
        {
            vacioDividido = true
        }

        if (divisor <=0)
        {
            vacioDivisor = false
        }
        else
        {
            vacioDivisor = true
        }

        if (cociente <=0)
        {
            vacioCociente = false
        }
        else
        {
            vacioCociente = true
        }

        if (residuo < 0)
        {
            vacioResiduo = false
        }
        else
        {
            vacioResiduo = true
        }

        if(divisor <= 0){
            vacioDivisor = false
            return false
        }else{
            vacioDivisor = true
        }


        if(dividendo == cociente * divisor + residuo)
        {
            validacionDividento = true
        }
        else
        {
            validacionDividento = false

            var concienteCalculado = dividendo / divisor
            var residuoCalculado = dividendo % divisor

            if (concienteCalculado!= cociente)
            {
                validarCociente =false
            }
            else
            {
                validarCociente =true
            }

            if(residuoCalculado!= residuo)
            {
                validarResiduo = false
            }
            else
            {
                validarResiduo = true
            }
        }

        if(divisor > dividendo)
        {
            validarDivisor = false
        }
        else
        {
            validarDivisor = true
        }

        return !(nombre == "" || dividendo == 0 || divisor == 0 || cociente == 0|| validacionDividento == false)
    }



    val dividir: StateFlow<List<Dividir>> = repository.getAll().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = emptyList()
    )

    fun save(){
        viewModelScope.launch {
            val dividir = Dividir(
                nombre = nombre,
                dividendo = dividendo,
                divisor = divisor,
                cociente = cociente,
                residuo = residuo,
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
        nombre = ""
        dividendo = 0
        divisor = 0
        cociente = 0
        residuo = 0

    }


}