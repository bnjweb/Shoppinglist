package com.example.shoppinglist.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.shoppinglist.model.Items

@Dao
interface ItemDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addItem(items: Items)

    @Query("SELECT * FROM ITEM_TABLE ORDER BY id ASC")
    fun readAllData():LiveData<List<Items>>

    @Update
    suspend fun modifyItems (items: Items)

    @Delete
    suspend fun delete(items: Items)

    @Query("DELETE FROM item_table")
    fun deleteAll()


}