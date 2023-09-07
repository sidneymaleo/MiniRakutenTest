package com.rakutentest.android.presentation.di

import com.rakutentest.android.data.repository.BuyBoxRepositoryImpl
import com.rakutentest.android.data.repository.ProductRepositoryImpl
import com.rakutentest.android.data.repository.dataSource.buyBox.BuyBoxLocalDataSource
import com.rakutentest.android.data.repository.dataSource.product.ProductLocalDataSource
import com.rakutentest.android.data.repository.dataSource.product.ProductRemoteDataSource
import com.rakutentest.android.domain.repository.BuyBoxRepository
import com.rakutentest.android.domain.repository.ProductRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    /**
     * Here we inject Product Repository Implementation
     */
    @Singleton
    @Provides
    fun provideProductRepository(
        productLocalDataSource: ProductLocalDataSource,
        productRemoteDataSource: ProductRemoteDataSource
    ) : ProductRepository {
        return ProductRepositoryImpl(
            productLocalDataSource = productLocalDataSource,
            productRemoteDataSource = productRemoteDataSource
        )
    }

    /**
     * Here we inject Buy Box Repository Implementation
     */
    @Singleton
    @Provides
    fun provideBuyBoxRepository(
        buyBoxLocalDataSource: BuyBoxLocalDataSource
    ): BuyBoxRepository {
        return BuyBoxRepositoryImpl(
            buyBoxLocalDataSource = buyBoxLocalDataSource
        )
    }

}