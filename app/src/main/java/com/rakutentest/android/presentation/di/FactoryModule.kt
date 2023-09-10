package com.rakutentest.android.presentation.di

import com.rakutentest.android.domain.useCase.buybox.GetLocalBuyBoxUseCase
import com.rakutentest.android.domain.useCase.buybox.SaveBuyBoxUseCase
import com.rakutentest.android.domain.useCase.product.DeleteLocalProductsUseCase
import com.rakutentest.android.domain.useCase.product.GetLocalProductsUseCase
import com.rakutentest.android.domain.useCase.product.GetRemoteProductDetailsUseCase
import com.rakutentest.android.domain.useCase.product.GetRemoteProductsUseCase
import com.rakutentest.android.domain.useCase.product.SaveProductUseCase
import com.rakutentest.android.presentation.viewModel.BuyBox.BuyBoxViewModelFactory
import com.rakutentest.android.presentation.viewModel.Product.ProductViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class FactoryModule {

    @Singleton
    @Provides
    fun provideProductViewModelFactory(
        getRemoteProductsUseCase: GetRemoteProductsUseCase,
        getRemoteProductDetailsUseCase: GetRemoteProductDetailsUseCase,
        getLocalProductsUseCase: GetLocalProductsUseCase,
        deleteLocalProductsUseCase: DeleteLocalProductsUseCase,
        saveProductUseCase: SaveProductUseCase,
        saveBuyBoxUseCase: SaveBuyBoxUseCase,
        getLocalBuyBoxUseCase: GetLocalBuyBoxUseCase
    ): ProductViewModelFactory {

        return ProductViewModelFactory(
            getRemoteProductsUseCase = getRemoteProductsUseCase,
            getRemoteProductDetailsUseCase = getRemoteProductDetailsUseCase,
            getLocalProductsUseCase = getLocalProductsUseCase,
            deleteLocalProductsUseCase = deleteLocalProductsUseCase,
            saveProductUseCase = saveProductUseCase,
            saveBuyBoxUseCase = saveBuyBoxUseCase,
            getLocalBuyBoxUseCase = getLocalBuyBoxUseCase
        )
    }

    @Singleton
    @Provides
    fun provideBuyBoxViewModelFactory(
        getLocalBuyBoxUseCase: GetLocalBuyBoxUseCase
    ): BuyBoxViewModelFactory {
        return BuyBoxViewModelFactory(
            getLocalBuyBoxUseCase = getLocalBuyBoxUseCase
        )
    }

}