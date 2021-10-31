package com.example.lab1.repository

import kotlinx.coroutines.flow.Flow
import com.example.lab1.database.Result

interface ResultRepository {
    suspend fun saveResultToDB(result: Result)
    fun getResults(): Flow<List<Result>>
    suspend fun deleteAllResultsFromDB()
}