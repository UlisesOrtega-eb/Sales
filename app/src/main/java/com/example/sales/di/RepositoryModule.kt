package com.example.sales.di

import com.example.sales.data.repository.CustomerRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import com.example.sales.data.repository.InMemoryCustomerRepository
import com.example.sales.data.repository.ProductRepositoryImpl
import com.example.sales.data.repository.RoomCustomerRepository
import com.example.sales.data.repository.RoomProductRepository
import com.example.sales.domain.repository.CustomerRepository
import com.example.sales.domain.repository.ProductRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindProductRepository(
        repository: ProductRepositoryImpl
    ): ProductRepository

    @Binds
    @Singleton
    abstract fun bindCustomerRepository(
        repository: CustomerRepositoryImpl
    ): CustomerRepository
}
