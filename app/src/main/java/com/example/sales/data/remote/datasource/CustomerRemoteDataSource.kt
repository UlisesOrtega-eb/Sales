package com.example.sales.data.remote.datasource

import com.example.sales.data.remote.api.CustomerApiService
import com.example.sales.data.remote.dto.CustomerDto
import javax.inject.Inject

class CustomerRemoteDataSource @Inject constructor(
    private val api: CustomerApiService
) {

    suspend fun getCustomers(): List<CustomerDto> {
        return api.getCustomers()
    }

    suspend fun findCustomerById(id: String): CustomerDto {
        return api.findCustomerById(id)
    }

    suspend fun saveCustomer(customer: CustomerDto): CustomerDto {
        return api.saveCustomer(customer)
    }

    suspend fun updateCustomer(id: String, customer: CustomerDto): CustomerDto {
        return api.updateCustomer(id, customer)
    }

    suspend fun deleteCustomer(id: String) {
        api.deleteCustomer(id)
    }
}
