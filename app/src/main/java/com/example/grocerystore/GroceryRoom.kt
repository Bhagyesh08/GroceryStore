package com.example.grocery

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import java.util.concurrent.locks.Lock


@Database(entities = [GroceryProducts::class],version=1)
abstract class GroceryRoom:RoomDatabase() {
    abstract fun getGroceryDao():DaoClass
    companion object{
        @Volatile
        private var instance:GroceryRoom?=null
        operator fun invoke(context: Context)= instance?: synchronized(this){
            instance?:buildDatabase(context).also{
                instance=it
            }
        }
        private fun buildDatabase(context: Context)=
            Room.databaseBuilder(
                context.applicationContext,GroceryRoom::class.java,"Grocery"

            ).build()
    }

}