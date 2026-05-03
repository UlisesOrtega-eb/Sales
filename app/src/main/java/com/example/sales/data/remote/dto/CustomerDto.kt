package com.example.sales.data.remote.dto

data class CustomerDto (
    val id: String,
    val name: String,
    val email: String,
    val purchaseHistory: List<String> = emptyList()
)
