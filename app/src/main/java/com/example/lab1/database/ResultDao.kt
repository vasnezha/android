package com.example.lab1.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.lab1.database.Result
import kotlinx.coroutines.flow.Flow

@Dao
interface ResultDao {
    @Insert
    suspend fun insertResult(result: Result)

    @Query("SELECT * FROM result_table")
    fun getAll(): Flow<List<Result>>

    @Query("DELETE FROM result_table")
    suspend fun deleteAll()
}