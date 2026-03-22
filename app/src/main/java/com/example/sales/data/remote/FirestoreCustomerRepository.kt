package com.example.sales.data.remote

import com.example.sales.domain.model.Customer
import com.example.sales.domain.repository.CustomerRepository
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow

class FirestoreCustomerRepository @Inject constructor(
    private val firebaseDataSource: CustomerFirebaseDataSource
) : CustomerRepository {
    override suspend fun saveCustomer(customer: Customer) {
        firebaseDataSource.saveCustomer(customer)
    }

    override suspend fun findCustomerById(customerId: String): Customer? {
        return firebaseDataSource.findCustomerByCode(customerId)
    }

    override suspend fun deleteCustomer(customerId: String) {
        firebaseDataSource.deleteCustomer(customerId)
    }

    override fun getCustomers(): Flow<List<Customer>> {
        return firebaseDataSource.getCustomers()
    }


}