package com.example.grocery

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class GroceryViewModel(private val repository: Repository):ViewModel() {
    fun insert(Products:GroceryProducts)=GlobalScope.launch{
        repository.insert(Products)
    }
    fun delete(Products: GroceryProducts)=GlobalScope.launch {
        repository.delete(Products)
    }
    fun getAllGroceryProducts()=repository.getAllProducts()
}