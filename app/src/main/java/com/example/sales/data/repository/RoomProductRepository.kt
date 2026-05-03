package com.example.sales.data.repository

import com.example.sales.data.local.dao.ProductDao
import com.example.sales.data.mapper.toDomain
import com.example.sales.data.mapper.toEntity
import com.example.sales.domain.model.Product
import com.example.sales.domain.repository.ProductRepository
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RoomProductRepository @Inject constructor(
    private val dao: ProductDao,
    private val firestore: FirebaseFirestore
) : ProductRepository {

    override fun getProducts(): Flow<List<Product>> {
        return dao.getProducts()
            .map { list -> list.map { it.toDomain() } }
    }

    override suspend fun findProductByCode(productCode: String): Product? {
        return dao.findByCode(productCode)?.toDomain()
    }

    override suspend fun saveProduct(product: Product) {
        dao.insert(product.toEntity())

        val productMap = hashMapOf(
            "code" to product.code,
            "description" to product.description,
            "category" to product.category,
            "price" to product.price,
            "stock" to product.stock,
            "taxable" to product.taxable
        )
        firestore.collection("products").document(product.code).set(productMap)
    }

    override suspend fun deleteProduct(productCode: String) {
        dao.deleteByCode(productCode)
        firestore.collection("products").document(productCode).delete()
    }
}