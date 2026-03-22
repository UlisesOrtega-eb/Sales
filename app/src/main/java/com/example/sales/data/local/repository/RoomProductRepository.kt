package com.example.sales.data.local.repository

import com.example.sales.data.local.dao.ProductDao
import com.example.sales.data.mapper.toDomain
import com.example.sales.data.mapper.toEntity
import com.example.sales.domain.model.Product
import com.example.sales.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
class RoomProductRepository @Inject constructor(
    private val dao: ProductDao
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
    }

    override suspend fun deleteProduct(productCode: String) {
        dao.deleteByCode(productCode)
    }
}