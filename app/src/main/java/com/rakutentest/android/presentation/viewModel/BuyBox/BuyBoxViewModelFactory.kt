package com.rakutentest.android.presentation.viewModel.BuyBox

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rakutentest.android.domain.useCase.buybox.GetLocalBuyBoxUseCase

class BuyBoxViewModelFactory(private val getLocalBuyBoxUseCase: GetLocalBuyBoxUseCase) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return BuyBoxViewModel(getLocalBuyBoxUseCase = getLocalBuyBoxUseCase) as T
    }
}