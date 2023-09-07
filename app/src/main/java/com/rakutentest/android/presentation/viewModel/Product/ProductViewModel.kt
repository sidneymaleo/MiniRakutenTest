package com.rakutentest.android.presentation.viewModel.Product

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rakutentest.android.domain.useCase.product.DeleteLocalProductsUseCase
import com.rakutentest.android.domain.useCase.product.GetLocalProductsUseCase
import com.rakutentest.android.domain.useCase.product.GetRemoteProductDetailsUseCase
import com.rakutentest.android.domain.useCase.product.GetRemoteProductsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val getRemoteProductsUseCase: GetRemoteProductsUseCase,
    private val getRemoteProductDetailsUseCase: GetRemoteProductDetailsUseCase,
    private val getLocalProductsUseCase: GetLocalProductsUseCase,
    private val deleteLocalProductsUseCase: DeleteLocalProductsUseCase
) : ViewModel() {


    fun getRemoteProducts(
        keyWord: String
    ) = viewModelScope.launch {
        try {
            val apiResult = getRemoteProductsUseCase.execute(keyWord = keyWord)
            apiResult.data?.let { products ->  

            }
        } catch (e: Exception) {
            
        }
    }

    fun getRemoteProductDetails(
        id: Int
    ) = viewModelScope.launch {
        try {
            val apiResult = getRemoteProductDetailsUseCase.execute(id = id)
            apiResult.data?.let {

            }
        }catch (e: Exception) {

        }
    }
}