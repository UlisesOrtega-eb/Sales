package com.example.sales.presentation.product.delete

import com.example.sales.domain.model.Product

data class DeleteProductUiState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val products: List<Product>
)