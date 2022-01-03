package com.example.grocery
import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface DaoClass {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun insert(item:GroceryProducts)
    @Delete
     fun delete(item:GroceryProducts)
    @Query("SELECT * FROM grocery_products")
    fun getAllGroceryProducts():LiveData<List<GroceryProducts>>
}