package com.example.sales.data.repository

import android.util.Log
import com.example.sales.data.local.datasource.ProductLocalDataSource
import com.example.sales.data.mapper.toDomain
import com.example.sales.data.mapper.toEntity
import com.example.sales.data.remote.datasource.ProductRemoteDataSource
import com.example.sales.data.remote.mapper.ProductRemoteMapper.toDomain
import com.example.sales.data.remote.mapper.ProductRemoteMapper.toDto
import com.example.sales.data.remote.mapper.ProductRemoteMapper.toEntity
import com.example.sales.domain.model.Product
import com.example.sales.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val remote: ProductRemoteDataSource,
    private val local: ProductLocalDataSource
) : ProductRepository {

    // 🔵 SOLO STREAM LOCAL (reactivo y estable)
    override fun getProducts(): Flow<List<Product>> {
        return local.getProducts()
            .map { list -> list.map { it.toDomain() } }
    }

    // 🟡 SYNC separado (NO dentro del Flow)
    suspend fun syncProducts() {
        try {
            val remoteProducts = remote.getProducts()
            local.replaceAll(remoteProducts.map { it.toEntity() })
        } catch (e: Exception) {
            Log.e("PRODUCT_SYNC", e.message ?: "Error syncing products", e)
        }
    }

    override suspend fun findProductByCode(productCode: String): Product? {
        val localProduct = local.findProductByCode(productCode)
        if (localProduct != null) return localProduct.toDomain()

        return try {
            val remoteProduct = remote.findProductByCode(productCode)
            local.saveProduct(remoteProduct.toEntity())
            remoteProduct.toDomain()
        } catch (e: Exception) {
            null
        }
    }

    override suspend fun saveProduct(product: Product) {
        try {
            remote.saveProduct(product.toDto())
        } catch (e: Exception) {
            Log.e("SAVE_PRODUCT", e.message ?: "Remote error", e)
        }
        local.saveProduct(product.toEntity())
    }

    override suspend fun deleteProduct(productCode: String) {
        try {
            remote.deleteProduct(productCode)
        } catch (e: Exception) {
            Log.e("DELETE_PRODUCT", e.message ?: "Remote error", e)
        }
        local.deleteProduct(productCode)
    }
}