package com.example.sales.data.repository

import com.example.sales.data.local.dao.CustomerDao
import com.example.sales.data.mapper.toDomain
import com.example.sales.data.mapper.toEntity
import com.example.sales.domain.model.Customer
import com.example.sales.domain.repository.CustomerRepository
import kotlinx.coroutines.flow.Flow
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RoomCustomerRepository @Inject constructor(
    private val dao: CustomerDao,
    private val firestore: FirebaseFirestore
) : CustomerRepository {

    override fun getCustomers(): Flow<List<Customer>> {
        return dao.getCustomers()
            .map { list -> list.map { it.toDomain() } }
    }

    override suspend fun findCustomerById(customerId: String): Customer? {
        return dao.findByCode(customerId)?.toDomain()
    }

    override suspend fun saveCustomer(customer: Customer) {
        dao.insert(customer.toEntity())

        val customerMap = hashMapOf(
            "code" to customer.id,
            "name" to customer.name,
            "email" to customer.email,
            "purchaseHistory" to customer.purchaseHistory
        )
        firestore.collection("customers").document(customer.id).set(customerMap)
    }

    override suspend fun deleteCustomer(customerId: String) {
        dao.deleteByCode(customerId)
        firestore.collection("customers").document(customerId).delete()
    }
}
