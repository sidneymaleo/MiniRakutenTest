package com.rakutentest.android.presentation.viewModel.Product

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rakutentest.android.domain.useCase.buybox.GetLocalBuyBoxUseCase
import com.rakutentest.android.domain.useCase.buybox.SaveBuyBoxUseCase
import com.rakutentest.android.domain.useCase.product.DeleteLocalProductsUseCase
import com.rakutentest.android.domain.useCase.product.GetLocalProductsUseCase
import com.rakutentest.android.domain.useCase.product.GetRemoteProductDetailsUseCase
import com.rakutentest.android.domain.useCase.product.GetRemoteProductsUseCase
import com.rakutentest.android.domain.useCase.product.SaveProductUseCase

class ProductViewModelFactory(
    private val getRemoteProductsUseCase: GetRemoteProductsUseCase,
    private val getRemoteProductDetailsUseCase: GetRemoteProductDetailsUseCase,
    private val getLocalProductsUseCase: GetLocalProductsUseCase,
    private val deleteLocalProductsUseCase: DeleteLocalProductsUseCase,
    private val saveProductUseCase: SaveProductUseCase,
    private val saveBuyBoxUseCase: SaveBuyBoxUseCase,
    private val getLocalBuyBoxUseCase: GetLocalBuyBoxUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ProductViewModel(
            getRemoteProductsUseCase = getRemoteProductsUseCase,
            getRemoteProductDetailsUseCase = getRemoteProductDetailsUseCase,
            getLocalProductsUseCase = getLocalProductsUseCase,
            deleteLocalProductsUseCase = deleteLocalProductsUseCase,
            saveProductUseCase = saveProductUseCase,
            saveBuyBoxUseCase = saveBuyBoxUseCase,
            getLocalBuyBoxUseCase = getLocalBuyBoxUseCase
        ) as T
    }
}