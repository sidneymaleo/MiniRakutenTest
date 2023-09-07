package com.rakutentest.android.presentation.di

import com.rakutentest.android.domain.repository.ProductRepository
import com.rakutentest.android.domain.useCase.product.DeleteLocalProductsUseCase
import com.rakutentest.android.domain.useCase.product.GetLocalProductsUseCase
import com.rakutentest.android.domain.useCase.product.GetRemoteProductDetailsUseCase
import com.rakutentest.android.domain.useCase.product.GetRemoteProductsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ProductUsecaseModule {

    @Singleton
    @Provides
    fun provideGetRemoteProductsUseCase(
        productRepository: ProductRepository
    ): GetRemoteProductsUseCase {
        return GetRemoteProductsUseCase(
            productRepository = productRepository
        )
    }

    @Singleton
    @Provides
    fun provideGetLocalProductsUseCase(
        productRepository: ProductRepository
    ) : GetLocalProductsUseCase {
        return GetLocalProductsUseCase(
            productRepository = productRepository
        )
    }

    @Singleton
    @Provides
    fun provideDeleteLocalProductsUseCase(
        productRepository: ProductRepository
    ): DeleteLocalProductsUseCase {
        return DeleteLocalProductsUseCase(
            productRepository = productRepository
        )
    }

    @Singleton
    @Provides
    fun provideGetRemoteProductDetailsUseCase(
        productRepository: ProductRepository
    ): GetRemoteProductDetailsUseCase {
        return GetRemoteProductDetailsUseCase(
            productRepository = productRepository
        )
    }
}