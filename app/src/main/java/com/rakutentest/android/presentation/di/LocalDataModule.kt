package com.rakutentest.android.presentation.di

import com.rakutentest.android.data.db.dao.BuyBoxDAO
import com.rakutentest.android.data.db.dao.ProductDAO
import com.rakutentest.android.data.repository.dataSource.buyBox.BuyBoxLocalDataSource
import com.rakutentest.android.data.repository.dataSource.product.ProductLocalDataSource
import com.rakutentest.android.data.repository.dataSourceImpl.buyBox.BuyBoxLocalDataSourceImpl
import com.rakutentest.android.data.repository.dataSourceImpl.product.ProductLocalDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LocalDataModule {

    @Singleton
    @Provides
    fun provideProductLocalDataSource(productDAO: ProductDAO): ProductLocalDataSource {
        return ProductLocalDataSourceImpl(productDAO = productDAO)
    }

    @Singleton
    @Provides
    fun proBuyBoxLocalDataSource(buyBoxDAO: BuyBoxDAO): BuyBoxLocalDataSource {
        return BuyBoxLocalDataSourceImpl(buyBoxDAO = buyBoxDAO)
    }
}