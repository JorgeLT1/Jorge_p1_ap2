package com.example.primerparcial.data.repository.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.primerparcial.data.repository.local.entities.Dividir
import kotlinx.coroutines.flow.Flow

@Dao
interface DividirDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(dividir: Dividir)

    @Query(
        """
            SELECT *
            FROM Dividores
            WHERE dividirId=:id
            LIMIT 1
        """
    )
    suspend fun find(id: Int) : Dividir?

    @Delete
    suspend fun delete(dividir: Dividir)

    @Query("SELECT * FROM Dividores")
    fun getAll() : Flow<List<Dividir>>

}
