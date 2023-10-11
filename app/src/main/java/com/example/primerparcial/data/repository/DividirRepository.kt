package com.example.primerparcial.data.repository

import com.example.primerparcial.data.repository.local.dao.DividirDao
import com.example.primerparcial.data.repository.local.entities.Dividir
import javax.inject.Inject


class DividirRepository @Inject constructor(private val dividirDao: DividirDao) {
    suspend fun save(dividir: Dividir) = dividirDao.save(dividir)
    suspend fun delete(dividir: Dividir) = dividirDao.delete(dividir)

    suspend fun find(id: Int) = dividirDao.find(id)
    fun getAll() = dividirDao.getAll()
}
