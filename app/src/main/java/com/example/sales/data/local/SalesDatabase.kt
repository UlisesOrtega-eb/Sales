package com.example.sales.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.sales.data.local.dao.CustomerDao
import com.example.sales.data.local.dao.ProductDao
import com.example.sales.data.local.entity.CustomerEntity
import com.example.sales.data.local.entity.ProductEntity

@Database(
    entities = [ProductEntity::class, CustomerEntity::class],
    version = 2,
    exportSchema = false
)
abstract class SalesDatabase : RoomDatabase() {

    abstract fun productDao(): ProductDao
    abstract fun customerDao(): CustomerDao
}
