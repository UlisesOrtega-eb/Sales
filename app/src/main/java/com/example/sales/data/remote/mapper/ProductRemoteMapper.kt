package com.example.sales.data.remote.mapper

import com.example.sales.data.local.entity.ProductEntity
import com.example.sales.data.remote.dto.ProductDto
import com.example.sales.domain.model.Product

object ProductRemoteMapper {

    fun ProductDto.toDomain(): Product = Product(
        code, description, category, price, stock, taxable
    )

    fun ProductDto.toEntity(): ProductEntity = ProductEntity(
        code, description, category, price, stock, taxable
    )

    fun Product.toDto(): ProductDto = ProductDto(
        code, description, category, price, stock, taxable
    )
}
