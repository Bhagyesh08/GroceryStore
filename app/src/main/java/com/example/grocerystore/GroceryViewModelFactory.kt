package com.example.grocery

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class GroceryViewModelFactory(private val repository: Repository):ViewModelProvider.NewInstanceFactory() {
    override fun <T:ViewModel?>create(modelClass: Class<T>):T{
        return GroceryViewModel(repository)as T
    }
}