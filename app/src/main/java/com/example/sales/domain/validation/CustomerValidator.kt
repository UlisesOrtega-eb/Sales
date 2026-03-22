package com.example.sales.domain.validation

import com.example.sales.domain.model.Customer
import com.example.sales.presentation.product.ValidationResult
import kotlin.collections.firstOrNull
import kotlin.let
import kotlin.takeIf
import kotlin.text.isBlank

class CustomerValidator {
    operator fun invoke(customer: Customer): ValidationResult =
        listOfNotNull(
            "Id required".takeIf { customer.id.isBlank() },
            "Name required".takeIf { customer.name.isBlank() },
            "Email required".takeIf { customer.email.isBlank() }
        ).firstOrNull(
            )?.let { ValidationResult.Error(it) }
            ?: ValidationResult.Success

}
