package com.example.grocery

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "grocery_products")
data class GroceryProducts (
    @ColumnInfo(name="Product")
    var Product:String,
    @ColumnInfo(name="Quantity")
    var Quantity: Int,
    @ColumnInfo(name="Price")
    var Price: Int,
){
    @PrimaryKey(autoGenerate = true)
    var id:Int=0
}