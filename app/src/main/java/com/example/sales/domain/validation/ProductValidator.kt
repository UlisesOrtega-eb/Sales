package com.example.sales.domain.validation

import com.example.sales.domain.model.Product
import com.example.sales.presentation.product.ValidationResult

class ProductValidator {

    operator fun invoke(product: Product): ValidationResult =
        listOfNotNull(
            "Code required".takeIf { product.code.isBlank() },
            "Description required".takeIf { product.description.isBlank() },
            "Category required".takeIf { product.category.isBlank() },
            "Invalid price".takeIf { product.price < 0 },
            "Invalid Stock".takeIf { product.stock <= 0 }
        ).firstOrNull()
            ?.let { ValidationResult.Error(it) }
            ?: ValidationResult.Success
}
