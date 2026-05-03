package com.example.sales.data.remote.api


data class ApiResponse<T>(
    val success: Boolean,
    val data: T
)