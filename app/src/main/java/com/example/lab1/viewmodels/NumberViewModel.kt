package com.example.lab1.viewmodels

import androidx.lifecycle.*
import com.example.lab1.database.Result
import kotlinx.coroutines.launch
import com.example.lab1.repository.ResultRepository

class NumberViewModel() : ViewModel() {

    private val _result = MutableLiveData<Double>()
    val result: LiveData<Double>
        get() = _result

    fun calculateSum(x: Double, y: Double) = viewModelScope.launch {
        _result.value = x + y
    }
}