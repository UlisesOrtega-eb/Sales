package com.example.sales.data.mapper

import com.example.sales.data.local.entity.CustomerEntity
import com.example.sales.domain.model.Customer


fun CustomerEntity.toDomain(): Customer {
    return Customer(
        id = code,
        name = name,
        email = email,
        purchaseHistory = if (purchaseHistory.isBlank()) emptyList() else purchaseHistory.split(",").map { it.trim() }
    )
}

fun Customer.toEntity(): CustomerEntity {
    return CustomerEntity(
        code = id,
        name = name,
        email = email,
        purchaseHistory = purchaseHistory.joinToString(", ")
    )
}