package com.example.sales.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.sales.data.local.entity.CustomerEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CustomerDao {

    @Query("SELECT * FROM customers")
    fun getCustomers(): Flow<List<CustomerEntity>>

    @Query("SELECT * FROM customers WHERE code = :code")
    suspend fun findByCode(code: String): CustomerEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(customer: CustomerEntity)

    @Query("DELETE FROM customers WHERE code = :code")
    suspend fun deleteByCode(code: String)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(customers: List<CustomerEntity>)

    @Query("DELETE FROM customers")
    suspend fun clearAll()

    @Transaction
    suspend fun replaceAll(customers: List<CustomerEntity>) {
        clearAll()
        insertAll(customers)
    }
}