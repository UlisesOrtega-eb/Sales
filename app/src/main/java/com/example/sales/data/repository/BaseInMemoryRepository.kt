package com.example.sales.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

abstract class BaseInMemoryRepository<T, ID>(
    initialData: List<T>
) : BaseRepository<T, ID> {

    protected val state = MutableStateFlow(initialData)

    abstract fun getId(item: T): ID

    override fun observeAll(): Flow<List<T>> = state

    override suspend fun findById(id: ID): T? {
        return state.value.find { getId(it) == id }
    }

    override suspend fun save(item: T) {
        state.update { current ->
            val index = current.indexOfFirst { getId(it) == getId(item) }

            if (index >= 0) {
                // 🔥 ACTUALIZA si ya existe
                current.toMutableList().apply {
                    this[index] = item
                }
            } else {
                // ➕ AGREGA si no existe
                current + item
            }
        }
    }

    override suspend fun deleteById(id: ID) {
        state.update { current ->
            current.filter { getId(it) != id }
        }
    }
}