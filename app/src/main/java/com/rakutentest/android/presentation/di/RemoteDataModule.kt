package com.rakutentest.android.presentation.di

import com.rakutentest.android.data.apiService.ProductAPIService
import com.rakutentest.android.data.repository.dataSource.product.ProductRemoteDataSource
import com.rakutentest.android.data.repository.dataSourceImpl.product.ProductRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RemoteDataModule {

    @Singleton
    @Provides
    fun provideProductRemoteDataSource(
        productAPIService: ProductAPIService
    ): ProductRemoteDataSource {
        return ProductRemoteDataSourceImpl(
            productAPIService = productAPIService
        )
    }
}