package com.example.sales.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import com.example.sales.data.local.repository.InMemoryCustomerRepository
import com.example.sales.data.local.repository.RoomCustomerRepository
import com.example.sales.data.local.repository.RoomProductRepository
import com.example.sales.data.remote.FirestoreCustomerRepository
import com.example.sales.data.remote.FirestoreProductRepository
import com.example.sales.domain.repository.CustomerRepository
import com.example.sales.domain.repository.ProductRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindProductRepository(
        repository: FirestoreProductRepository //RoomProductRepository //InMemoryProductRepository
    ): ProductRepository

    @Binds
    @Singleton
    abstract fun bindCustomerRepository(
        repository: FirestoreCustomerRepository //RoomCustomerRepository //InMemoryCustomerRepository
    ): CustomerRepository
}
