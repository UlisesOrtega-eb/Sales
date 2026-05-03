package com.example.sales.data.remote.datasource

import com.example.sales.data.remote.api.ProductApiService
import com.example.sales.data.remote.dto.ProductDto
import javax.inject.Inject

class ProductRemoteDataSource @Inject constructor(
    private val api: ProductApiService
) {

    suspend fun getProducts(): List<ProductDto> {
        return api.getProducts().data
    }

    suspend fun findProductByCode(code: String): ProductDto {
        return api.findProductByCode(code)
    }

    suspend fun saveProduct(product: ProductDto): ProductDto {
        return api.saveProduct(product).data
    }

    suspend fun updateProduct(code: String, product: ProductDto): ProductDto {
        return api.updateProduct(code, product).data
    }

    suspend fun deleteProduct(code: String) {
        api.deleteProduct(code)
    }
}