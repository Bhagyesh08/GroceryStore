package com.example.grocery

class Repository (private val el:GroceryRoom){
    suspend fun insert(Products:GroceryProducts)=el.getGroceryDao().insert(Products)
    suspend fun delete(Products:GroceryProducts)=el.getGroceryDao().delete(Products)
    fun getAllProducts()=el.getGroceryDao().getAllGroceryProducts()
}