package com.example.sales.presentation.product.delete

sealed class DeleteProductUiEvent {
    data class DeleteClicked(val productId: String) : DeleteProductUiEvent()
}