package com.example.sales.presentation.customer.delete

sealed interface DeleteCustomerUiEffect {
    data class ShowSuccess(val message: String) : DeleteCustomerUiEffect
    data class ShowError(val message: String) : DeleteCustomerUiEffect
    object NavigateBack : DeleteCustomerUiEffect
}