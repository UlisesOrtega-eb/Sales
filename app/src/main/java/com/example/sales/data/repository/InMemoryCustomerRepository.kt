package com.example.sales.data.repository

import com.example.sales.domain.model.Customer
import com.example.sales.domain.repository.CustomerRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class InMemoryCustomerRepository @Inject constructor()
    : BaseInMemoryRepository<Customer, String>(
    initialData = listOf(
        Customer("C1", "Juan Perez", "juan@email.com", listOf("Compró laptop")),
        Customer("C2", "Maria Lopez", "maria@email.com", listOf("Compró mouse")),
        Customer("C3", "Carlos Ruiz", "carlos@email.com", listOf("Sin compras"))
    )
),
    CustomerRepository {
    override fun getId(item: Customer): String = item.id

    override fun observeAll(): Flow<List<Customer>> = state

    override suspend fun findCustomerById(customerId: String): Customer? {
        return findById(customerId)
    }

    override suspend fun saveCustomer(customer: Customer) {
        save(customer)
    }

    override suspend fun deleteCustomer(customerId: String) {
        deleteById(customerId)
    }

    override fun getCustomers(): Flow<List<Customer>> = observeAll()
}