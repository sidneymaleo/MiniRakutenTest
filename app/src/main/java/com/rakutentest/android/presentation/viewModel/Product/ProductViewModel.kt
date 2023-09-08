package com.rakutentest.android.presentation.viewModel.Product

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rakutentest.android.domain.useCase.product.DeleteLocalProductsUseCase
import com.rakutentest.android.domain.useCase.product.GetLocalProductsUseCase
import com.rakutentest.android.domain.useCase.product.GetRemoteProductDetailsUseCase
import com.rakutentest.android.domain.useCase.product.GetRemoteProductsUseCase
import com.rakutentest.android.ui.UIEvent.ScreenState.ProductDetailsScreenState
import com.rakutentest.android.ui.UIEvent.ScreenState.ProductListScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import androidx.compose.runtime.State
import com.rakutentest.android.presentation.util.isNetworkAvailable
import com.rakutentest.android.ui.UIEvent.Event.ProductEvent
import com.rakutentest.android.ui.UIEvent.UIEvent
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val getRemoteProductsUseCase: GetRemoteProductsUseCase,
    private val getRemoteProductDetailsUseCase: GetRemoteProductDetailsUseCase,
    private val getLocalProductsUseCase: GetLocalProductsUseCase,
    private val deleteLocalProductsUseCase: DeleteLocalProductsUseCase
) : ViewModel() {

    /**
     * Here we initialize our product screen state
     * This attribute help use to observe in the viewModel
     * The state of our product list ui
     */
    private val _screenStateProducts= mutableStateOf(
        ProductListScreenState()
    )

    /**
     * Here we initialize our product details screen state
     * This attribute help use to observe in the viewModel
     * The state of our product details ui
     */
    private val _screenStateProductDetails = mutableStateOf(
        ProductDetailsScreenState()
    )


    /**
     * Here we call the State Class to activate the State driven in our product list & product details entities
     */
    val screenStateProducts: State<ProductListScreenState> = _screenStateProducts
    val screenStateProductDetails: State<ProductDetailsScreenState> = _screenStateProductDetails

    //This Flow help use to displaying our error message with the snack bar ui component
    private val _uiEventFlow = MutableSharedFlow<UIEvent>()
    val uiEventFlow = _uiEventFlow.asSharedFlow()


    // Here we make our http Request to get our product list
    fun getRemoteProducts(
        keyWord: String
    ) = viewModelScope.launch {
        try {
            val apiResult = getRemoteProductsUseCase.execute(keyWord = keyWord)
            apiResult.data?.let { products ->
                /**
                 * I use the addAll method because
                 * if we have several pages we can
                 * apply lazzy loading infinitely
                 */
                screenStateProducts.value.productList.addAll(products.products)
                //Log.d("TestingTesting1", "${products.products.toString()}")
                // Here we upgrade our state
                _screenStateProducts.value = _screenStateProducts.value.copy(
                    isLoad = false,
                    isNetworkConnected = true,
                    isNetworkError = false,
                    isRequested = false
                )
            }
        } catch (e: Exception) {
            _screenStateProducts.value = _screenStateProducts.value.copy(
                isNetworkConnected = true,
                isNetworkError = true,
                isLoad = false,
                isRequested = false
            )
        }
    }


    // Here we make our http Request to get our product details
    fun getRemoteProductDetails(
        id: Int
    ) = viewModelScope.launch {
        try {
            val apiResult = getRemoteProductDetailsUseCase.execute(id = id)
            apiResult.data?.let { productDetails ->
                _screenStateProductDetails.value = _screenStateProductDetails.value.copy(
                    isLoad = false,
                    isNetworkConnected = true,
                    isNetworkError = false,
                    productDetails = productDetails
                )
            }
        }catch (e: Exception) {

        }
    }


    //in this method we handle our product list event
    fun onEvent(event: ProductEvent) {
        when(event) {
            //Here we get call our remote product
            is ProductEvent.GetRemoteProducts -> {
                //we activate our progress par spinner
                _screenStateProducts.value = _screenStateProducts.value.copy(
                    isLoad = true
                )

                //we check the network state
                if (isNetworkAvailable(event.app)) {
                    //we call our remote product list
                    getRemoteProducts(
                        keyWord = event.keyWord
                    )
                } else {
                    _screenStateProducts.value = _screenStateProducts.value.copy(
                        //we have't  the network connexion
                        isNetworkConnected = false,
                        //if we have't the network error
                        isNetworkError = false,
                        isLoad = false,
                    )
                }
            }

            is ProductEvent.GetRemoteProductDetails -> {
                _screenStateProductDetails.value = _screenStateProductDetails.value.copy(
                    isLoad = true
                )

                //we check the network state
                if (isNetworkAvailable(event.app)) {
                    //we call our remote product details
                    getRemoteProductDetails(
                        id = event.id
                    )
                } else {
                    _screenStateProductDetails.value = _screenStateProductDetails.value.copy(
                        //we have't  the network connexion
                        isNetworkConnected = false,
                        //if we have't the network error
                        isNetworkError = false,
                        isLoad = false,
                    )
                }
            }

            is ProductEvent.GetLocalProducts -> {

            }

            is ProductEvent.DeleteLocalProductsUseCase -> {

            }

            //we control our network errors
            is ProductEvent.IsNetworkConnected -> {
                viewModelScope.launch {
                    _uiEventFlow.emit(
                        UIEvent.ShowMessage(
                            message = event.errorMessage
                        )
                    )
                }
            }

            is ProductEvent.IsNetworkError -> {
                viewModelScope.launch {
                    _uiEventFlow.emit(
                        UIEvent.ShowMessage(
                            message = event.errorMessage
                        )
                    )
                }
            }
        }
    }
}