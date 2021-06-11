package com.example.shoppinglist.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.shoppinglist.data.ItemDatabase
import com.example.shoppinglist.repository.ItemRepository
import com.example.shoppinglist.model.Items
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class
ItemViewModel(application: Application): AndroidViewModel(application) {

     val readAllData: LiveData<List<Items>>
    private val repository: ItemRepository
    init {
        val itemDao = ItemDatabase.getDatabase(
            application
        ).itemDao()
        repository = ItemRepository(itemDao)
        readAllData = repository.readAllData
    }
    fun addItem(items: Items){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addItem(items)
        }
    }
    fun modifyItems(items: Items){
        viewModelScope.launch (Dispatchers.IO){
            repository.modifyItems(items)
        }

    }
    fun delete(items: Items){
        viewModelScope.launch(Dispatchers.IO){
            repository.delete(items)
        }
    }



}