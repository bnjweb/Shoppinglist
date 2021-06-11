package com.example.shoppinglist.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "item_table")
data class Items (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val shop: String,
    val amount: Int,
    val price: Int,
    val image: String,
    var checkmark: Boolean
): Parcelable