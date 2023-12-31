package com.rakutentest.android.presentation.di

import com.rakutentest.android.domain.repository.BuyBoxRepository
import com.rakutentest.android.domain.useCase.buybox.GetLocalBuyBoxUseCase
import com.rakutentest.android.domain.useCase.buybox.SaveBuyBoxUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class BuyBoxUseCaseModule {

    @Singleton
    @Provides
    fun provideGetLocalBuyBoxUseCase(
        buyBoxRepository: BuyBoxRepository
    ): GetLocalBuyBoxUseCase {
        return GetLocalBuyBoxUseCase(
            buyBoxRepository = buyBoxRepository
        )
    }

    @Singleton
    @Provides
    fun provideBuyBoxUseCase(
        buyBoxRepository: BuyBoxRepository
    ): SaveBuyBoxUseCase {
        return SaveBuyBoxUseCase(
            buyBoxRepository = buyBoxRepository
        )
    }
}