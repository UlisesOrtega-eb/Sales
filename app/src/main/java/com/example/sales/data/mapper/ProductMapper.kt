package com.example.sales.data.mapper

import com.example.sales.data.local.entity.ProductEntity
import com.example.sales.domain.model.Product

fun ProductEntity.toDomain(): Product {
    return Product(
        code,
        description,
        category,
        price,
        stock,
        taxable
    )
}

fun Product.toEntity(): ProductEntity {
    return ProductEntity(
        code,
        description,
        category,
        price,
        stock,
        taxable
    )
}
