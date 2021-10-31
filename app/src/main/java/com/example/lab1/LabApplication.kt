package com.example.lab1

import android.app.Application
import com.example.lab1.database.LabDatabase
import com.example.lab1.repository.ResultRepositoryImpl

class LabApplication : Application() {

    private val database by lazy { LabDatabase.getDatabase(this) }

    val repository by lazy { ResultRepositoryImpl(database.resultDao()) }
}