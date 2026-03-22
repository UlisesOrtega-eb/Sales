package com.example.sales.domain.usecase.product

import com.example.sales.domain.model.Product
import com.example.sales.domain.repository.ProductRepository
import javax.inject.Inject

class CreateProductUseCase @Inject constructor(
    private val repository: ProductRepository
){
    suspend operator fun invoke(product: Product){
        val existing = repository.findProductByCode(productCode = product.code)

        require(existing==null){
            "Product with code: ${product.code} already exists"
        }

        repository.saveProduct(product)
    }
}