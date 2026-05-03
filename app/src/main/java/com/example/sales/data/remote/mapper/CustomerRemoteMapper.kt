package com.example.sales.data.remote.mapper

import com.example.sales.data.local.entity.CustomerEntity
import com.example.sales.data.remote.dto.CustomerDto
import com.example.sales.domain.model.Customer

object CustomerRemoteMapper {

    fun CustomerDto.toDomain(): Customer = Customer(
        id = id,
        name = name,
        email = email,
        purchaseHistory = purchaseHistory
    )

    fun CustomerDto.toEntity(): CustomerEntity = CustomerEntity(
        code = id,
        name = name,
        email = email,
        purchaseHistory = purchaseHistory.joinToString(", ")
    )

    fun Customer.toDto(): CustomerDto = CustomerDto(
        id = id,
        name = name,
        email = email,
        purchaseHistory = purchaseHistory
    )
}
