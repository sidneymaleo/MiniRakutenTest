package com.rakutentest.android.presentation.viewModel.Product

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rakutentest.android.domain.useCase.product.DeleteLocalProductsUseCase
import com.rakutentest.android.domain.useCase.product.GetLocalProductsUseCase
import com.rakutentest.android.domain.useCase.product.GetRemoteProductDetailsUseCase
import com.rakutentest.android.domain.useCase.product.GetRemoteProductsUseCase

class ProductViewModelFactory(
    private val getRemoteProductsUseCase: GetRemoteProductsUseCase,
    private val getRemoteProductDetailsUseCase: GetRemoteProductDetailsUseCase,
    private val getLocalProductsUseCase: GetLocalProductsUseCase,
    private val deleteLocalProductsUseCase: DeleteLocalProductsUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ProductViewModel(
            getRemoteProductsUseCase = getRemoteProductsUseCase,
            getRemoteProductDetailsUseCase = getRemoteProductDetailsUseCase,
            getLocalProductsUseCase = getLocalProductsUseCase,
            deleteLocalProductsUseCase = deleteLocalProductsUseCase
        ) as T
    }
}