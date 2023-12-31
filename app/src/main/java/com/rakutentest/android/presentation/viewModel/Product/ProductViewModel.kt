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
import androidx.lifecycle.liveData
import com.rakutentest.android.data.model.dataLocal.BuyboxRoom
import com.rakutentest.android.data.model.dataLocal.ProductRoom
import com.rakutentest.android.data.model.dataRemote.response.Buybox
import com.rakutentest.android.data.model.dataRemote.response.Product
import com.rakutentest.android.domain.useCase.buybox.GetLocalBuyBoxUseCase
import com.rakutentest.android.domain.useCase.buybox.SaveBuyBoxUseCase
import com.rakutentest.android.domain.useCase.product.SaveProductUseCase
import com.rakutentest.android.presentation.util.isNetworkAvailable
import com.rakutentest.android.ui.UIEvent.Event.ProductEvent
import com.rakutentest.android.ui.UIEvent.UIEvent
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val getRemoteProductsUseCase: GetRemoteProductsUseCase,
    private val getRemoteProductDetailsUseCase: GetRemoteProductDetailsUseCase,
    private val getLocalProductsUseCase: GetLocalProductsUseCase,
    private val deleteLocalProductsUseCase: DeleteLocalProductsUseCase,
    private val saveProductUseCase: SaveProductUseCase,
    private val saveBuyBoxUseCase: SaveBuyBoxUseCase,
    private val getLocalBuyBoxUseCase: GetLocalBuyBoxUseCase
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
            //on supprime notre cache database avant de recharger les nouvelles données si l'utilisateur est en ligne
            deleteLocalProductsUseCase.execute()
            val apiResult = getRemoteProductsUseCase.execute(keyWord = keyWord)
            apiResult.data?.let { products ->
                /**
                 * I use the addAll method because
                 * if we have several pages we can
                 * apply lazzy loading infinitely
                 */
                screenStateProducts.value.productList.addAll(products.products)
                // Here we upgrade our state
                _screenStateProducts.value = _screenStateProducts.value.copy(
                    isLoad = false,
                    isNetworkConnected = true,
                    isNetworkError = false,
                    isRequested = false
                )
                //here we insert our product

                products.products.forEach { product ->
                    //here we save in our product table
                    saveProductUseCase.execute(product =
                        ProductRoom(
                            id = product.id,
                            newBestPrice = product.newBestPrice,
                            usedBestPrice = product.usedBestPrice,
                            headline = product.headline,
                            reviewsAverageNote = product.reviewsAverageNote,
                            nbReviews = product.nbReviews,
                            categoryRef = product.categoryRef,
                            imagesUrls = product.imagesUrls
                        )
                    )
                    //here we save in our buybox
                    saveBuyBoxUseCase.execute(buyBox =
                        BuyboxRoom(
                            //the id is autoIncrement
                            id = 0,
                            salePrice = product.buybox.salePrice,
                            advertType = product.buybox.advertType,
                            advertQuality = product.buybox.advertQuality,
                            saleCrossedPrice = product.buybox.saleCrossedPrice,
                            salePercentDiscount = product.buybox.salePercentDiscount,
                            isRefurbished = product.buybox.isRefurbished,
                            productId = product.id
                        )
                    )
                }



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

    fun getProductList() = liveData {
        getLocalProductsUseCase.execute().collect {
            emit(it)
        }
    }

    fun getBuyBox(productId: Long) = liveData {
        getLocalBuyBoxUseCase.execute(productId = productId).collect {
            emit(it)
        }
    }


    // Here we make our http Request to get our product details
    fun getRemoteProductDetails(
        id: Long
    ) = viewModelScope.launch {
        try {
            val apiResult = getRemoteProductDetailsUseCase.execute(id = id)
            apiResult.data?.let { productDetails ->
                _screenStateProductDetails.value = _screenStateProductDetails.value.copy(
                    isLoad = false,
                    isNetworkConnected = true,
                    isNetworkError = false,
                    productDetails = productDetails,
                    isRequested = false
                )
            }
        } catch (e: Exception) {
            _screenStateProductDetails.value = _screenStateProductDetails.value.copy(
                isNetworkConnected = true,
                isNetworkError = true,
                isLoad = false,
                isRequested = false
            )
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
                // Before we clean our screen state product list
                screenStateProducts.value.productList.removeAll(screenStateProducts.value.productList)
                viewModelScope.launch {

                }
            }

            is ProductEvent.DeleteLocalProductsUseCase -> {

            }

            is ProductEvent.IsInternalError -> {
                viewModelScope.launch {
                    _uiEventFlow.emit(
                        UIEvent.ShowMessage(
                            message = event.errorMessage
                        )
                    )
                }
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

            else -> {}
        }
    }
}