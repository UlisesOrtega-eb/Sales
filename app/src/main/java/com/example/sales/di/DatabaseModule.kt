package com.example.sales.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import com.example.sales.data.local.SalesDatabase
import com.example.sales.data.local.dao.CustomerDao
import com.example.sales.data.local.dao.ProductDao
import javax.inject.Singleton
@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): SalesDatabase {
        return Room.databaseBuilder(
            context,
            SalesDatabase::class.java,
            "sales.db"
        ).fallbackToDestructiveMigration(false)
            .build()
    }

    @Provides
    fun provideProductDao(
        database: SalesDatabase
    ): ProductDao {
        return database.productDao()
    }
    @Provides
    fun provideCustomerDao(
        database: SalesDatabase
    ): CustomerDao {
        return database.customerDao()
    }
}
