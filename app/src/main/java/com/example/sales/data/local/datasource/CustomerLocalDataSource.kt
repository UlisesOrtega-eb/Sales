package com.example.sales.data.local.datasource

import com.example.sales.data.local.dao.CustomerDao
import com.example.sales.data.local.entity.CustomerEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CustomerLocalDataSource @Inject constructor(
    private val dao: CustomerDao
) {

    fun getCustomers(): Flow<List<CustomerEntity>> {
        return dao.getCustomers()
    }

    suspend fun findCustomerByCode(customerCode: String): CustomerEntity? {
        return dao.findByCode(customerCode)
    }

    suspend fun saveCustomer(customer: CustomerEntity) {
        dao.insert(customer)
    }

    suspend fun deleteCustomer(customerCode: String) {
        dao.deleteByCode(customerCode)
    }

    suspend fun saveCustomers(customers: List<CustomerEntity>) {
        dao.insertAll(customers)
    }

    suspend fun replaceAll(customers: List<CustomerEntity>) {
        dao.replaceAll(customers)
    }


}