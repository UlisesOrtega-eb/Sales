package com.example.sales.presentation.customer.delete

sealed class DeleteCustomerUiEvent {
    data class DeleteClicked(val customerId: String) : DeleteCustomerUiEvent()
}
