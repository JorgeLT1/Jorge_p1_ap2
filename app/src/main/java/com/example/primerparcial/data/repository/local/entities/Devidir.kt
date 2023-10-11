package com.example.primerparcial.data.repository.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Dividores")
data class Dividir(
    @PrimaryKey
    val DividirId : Int? = null,
    var nombre: String = "",
    var dividido: Int? = null,
    var divisor: Int? = null,
    var cociente: Int? = null,
    var residuo: Int? = null,
)
