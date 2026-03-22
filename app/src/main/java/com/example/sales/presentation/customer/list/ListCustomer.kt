package com.example.sales.presentation.customer.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.sales.domain.model.Customer
import com.example.sales.presentation.customer.delete.DeleteCustomerUiEvent
import com.example.sales.presentation.customer.delete.DeleteCustomerViewModel

@Composable
fun ListCustomer(
    customers: List<Customer>,
    viewModel: DeleteCustomerViewModel = hiltViewModel()
) {


    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(
            items = customers,
            key = { it.id }
        ) { customer ->
            CustomerItem(customer = customer,
                    onDelete = { customer->
                // Lógica para eliminar del repositorio o ViewModel
                        viewModel.onEvent(
                            DeleteCustomerUiEvent.DeleteClicked(customer.id)
                        )
            })
        }
    }
}
