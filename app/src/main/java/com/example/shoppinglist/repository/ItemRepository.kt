package com.example.shoppinglist.repository

import androidx.lifecycle.LiveData
import com.example.shoppinglist.data.ItemDao
import com.example.shoppinglist.model.Items

class ItemRepository(private val itemDao: ItemDao) {
    val readAllData: LiveData<List<Items>> = itemDao.readAllData()

    suspend fun addItem(items: Items){
        itemDao.addItem(items)

    }

    suspend fun modifyItems(items: Items){
        itemDao.modifyItems(items)
    }

    suspend fun delete(items: Items){
        itemDao.delete(items)
    }


}
