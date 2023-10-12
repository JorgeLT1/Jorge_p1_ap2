package com.example.primerparcial.data.repository.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Dividores")
data class Dividir(
    @PrimaryKey
    val dividirId : Int? = null,
    var nombre: String = "",
    var dividido: Double? = null,
    var divisor: Double? = null,
    var cociente: Double? = null,
    var residuo: Double? = null,
)
