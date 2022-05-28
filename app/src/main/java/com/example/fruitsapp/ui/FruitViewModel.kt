package com.example.fruitsapp.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fruitsapp.network.Fruit
import com.example.fruitsapp.network.FruitApi
import kotlinx.coroutines.launch

enum class FruitApiStatus {LOADING, ERROR, DONE}

class FruitViewModel : ViewModel() {
    private val _status = MutableLiveData<FruitApiStatus>()
    val status: LiveData<FruitApiStatus> = _status

    private val _fruits = MutableLiveData<List<Fruit>>()
    val fruits: LiveData<List<Fruit>> = _fruits

    private val _fruit = MutableLiveData<Fruit>()
    val fruit: LiveData<Fruit> = _fruit

    init{
        getFruitList()
    }

    fun getFruitList() {
        _status.value = FruitApiStatus.LOADING
        viewModelScope.launch {
            try {
                _fruits.value = FruitApi.retrofitService.getFruits()
                _status.value = FruitApiStatus.DONE
            } catch (e: Exception) {
                e.printStackTrace()
                _status.value = FruitApiStatus.ERROR
            }
        }
    }

    fun onFruitClicked(fruit: Fruit) {
        _fruit.value = fruit
    }
}