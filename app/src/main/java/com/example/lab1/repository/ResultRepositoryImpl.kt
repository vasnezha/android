package com.example.lab1.repository

import kotlinx.coroutines.flow.Flow
import com.example.lab1.database.Result
import com.example.lab1.database.ResultDao

class ResultRepositoryImpl(
    private val resultDao: ResultDao
) : ResultRepository {

    override suspend fun saveResultToDB(result: Result) = resultDao.insertResult(result)

    override fun getResults(): Flow<List<Result>> = resultDao.getAll()

    override suspend fun deleteAllResultsFromDB() = resultDao.deleteAll()

}