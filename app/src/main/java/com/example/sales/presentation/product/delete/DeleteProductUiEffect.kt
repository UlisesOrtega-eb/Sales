package com.example.sales.presentation.product.delete

sealed interface DeleteProductUiEffect {
    data class ShowSuccess(val message: String) : DeleteProductUiEffect
    data class ShowError(val message: String) : DeleteProductUiEffect
    object NavigateBack : DeleteProductUiEffect
}