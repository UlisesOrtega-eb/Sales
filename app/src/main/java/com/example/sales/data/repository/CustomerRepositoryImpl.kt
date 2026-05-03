package com.example.sales.data.repository

import android.util.Log
import com.example.sales.data.local.datasource.CustomerLocalDataSource
import com.example.sales.data.mapper.toDomain
import com.example.sales.data.mapper.toEntity
import com.example.sales.data.remote.datasource.CustomerRemoteDataSource
import com.example.sales.data.remote.mapper.CustomerRemoteMapper.toDomain
import com.example.sales.data.remote.mapper.CustomerRemoteMapper.toDto
import com.example.sales.data.remote.mapper.CustomerRemoteMapper.toEntity
import com.example.sales.domain.model.Customer
import com.example.sales.domain.repository.CustomerRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CustomerRepositoryImpl @Inject constructor(
    private val remote: CustomerRemoteDataSource,
    private val local: CustomerLocalDataSource
) : CustomerRepository {

    // 🔵 SOLO STREAM LOCAL
    override fun getCustomers(): Flow<List<Customer>> {
        return local.getCustomers()
            .map { list -> list.map { it.toDomain() } }
    }

    // 🟡 SYNC separado
    suspend fun syncCustomers() {
        try {
            val remoteCustomers = remote.getCustomers()
            local.replaceAll(remoteCustomers.map { it.toEntity() })
        } catch (e: Exception) {
            Log.e("CUSTOMER_SYNC", e.message ?: "Error syncing customers", e)
        }
    }

    override suspend fun saveCustomer(customer: Customer) {
        try {
            remote.saveCustomer(customer.toDto())
        } catch (e: Exception) {
            Log.e("SAVE_CUSTOMER", e.message ?: "Remote error", e)
        }
        local.saveCustomer(customer.toEntity())
    }

    override suspend fun findCustomerById(customerId: String): Customer? {
        val localCustomer = local.findCustomerByCode(customerId)
        if (localCustomer != null) return localCustomer.toDomain()

        return try {
            val remoteCustomer = remote.findCustomerById(customerId)
            local.saveCustomer(remoteCustomer.toEntity())
            remoteCustomer.toDomain()
        } catch (e: Exception) {
            null
        }
    }

    override suspend fun deleteCustomer(customerId: String) {
        try {
            remote.deleteCustomer(customerId)
        } catch (e: Exception) {
            Log.e("DELETE_CUSTOMER", e.message ?: "Remote error", e)
        }
        local.deleteCustomer(customerId)
    }
}
