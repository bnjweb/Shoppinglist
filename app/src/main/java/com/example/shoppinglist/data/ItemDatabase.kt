package com.example.shoppinglist.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.shoppinglist.model.Items

@Database(entities = [Items::class], version = 2, exportSchema = false) //no history data
abstract class ItemDatabase: RoomDatabase() {
    abstract fun itemDao(): ItemDao

    companion object{
        @Volatile
        private var INSTANCE: ItemDatabase? = null

        fun getDatabase(context: Context): ItemDatabase {
            val tInstance = INSTANCE
            if (tInstance != null){
                return tInstance
            }
            synchronized(this){
            val instance = Room.databaseBuilder(
                context.applicationContext,
                ItemDatabase::class.java,
                "items_database"

            ).build()
                INSTANCE = instance
                return instance
        }
        }
    }
}
