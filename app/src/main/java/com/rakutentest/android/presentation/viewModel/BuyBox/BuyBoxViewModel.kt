package com.rakutentest.android.presentation.viewModel.BuyBox

import androidx.lifecycle.ViewModel
import com.rakutentest.android.domain.useCase.buybox.GetLocalBuyBoxUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BuyBoxViewModel @Inject constructor(
    private val getLocalBuyBoxUseCase: GetLocalBuyBoxUseCase
) : ViewModel() {

}