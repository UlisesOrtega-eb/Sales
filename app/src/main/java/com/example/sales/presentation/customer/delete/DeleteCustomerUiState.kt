package com.example.sales.presentation.customer.delete

import com.example.sales.domain.model.Customer

data class DeleteCustomerUiState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val customers: List<Customer>
)
