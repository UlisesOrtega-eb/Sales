package com.example.sales.data.local.repository

import com.example.sales.data.local.dao.CustomerDao
import com.example.sales.data.mapper.toDomain
import com.example.sales.data.mapper.toEntity
import com.example.sales.domain.model.Customer
import com.example.sales.domain.repository.CustomerRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RoomCustomerRepository @Inject constructor(
    private val dao: CustomerDao
) : CustomerRepository{
    override suspend fun saveCustomer(customer: Customer) {
        dao.insert(customer.toEntity())
    }

    override suspend fun deleteCustomer(customerId: String) {
        dao.deleteById(customerId)
    }

    override suspend fun findCustomerById(customerId: String): Customer? {
        return dao.findById(customerId)?.toDomain()
    }

    override fun getCustomers(): Flow<List<Customer>> {
        return dao.getCustomers()
            .map {
                    list -> list.map { it.toDomain() }
            }
    }
}
