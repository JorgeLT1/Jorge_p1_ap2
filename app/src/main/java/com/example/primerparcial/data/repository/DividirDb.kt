package com.example.primerparcial.data.repository

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.primerparcial.data.repository.local.dao.DividirDao
import com.example.primerparcial.data.repository.local.entities.Dividir


@Database(
    entities = [Dividir::class],
    version = 3
)
abstract class Database : RoomDatabase(){
    abstract fun DividirDao() : DividirDao
}