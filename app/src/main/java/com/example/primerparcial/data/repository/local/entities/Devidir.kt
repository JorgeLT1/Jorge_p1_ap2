package com.example.primerparcial.data.repository.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Dividores")
data class Dividir(
    @PrimaryKey
    val dividirId : Int? = null,
    var nombre: String = "",
    var dividendo: Int? = null,
    var divisor: Int? = null,
    var cociente: Int? = null,
    var residuo: Int? = null,
)
