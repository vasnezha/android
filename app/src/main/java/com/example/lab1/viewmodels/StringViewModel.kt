package com.example.lab1.viewmodels

import androidx.lifecycle.*
import com.example.lab1.database.Result
import com.example.lab1.repository.ResultRepository
import kotlinx.coroutines.launch

class StringViewModel(
    private val repository: ResultRepository
) : ViewModel() {

    private val _result = MutableLiveData<String>()
    val result: LiveData<String>
        get() = _result

    fun saveResult(result: Result) = viewModelScope.launch {
        repository.saveResultToDB(result)
    }

    fun concatenateStrings(name: String, surname: String) = viewModelScope.launch {
        _result.value = "$name $surname"
    }

    class Factory(private val repo: ResultRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(StringViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return StringViewModel(repo) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}