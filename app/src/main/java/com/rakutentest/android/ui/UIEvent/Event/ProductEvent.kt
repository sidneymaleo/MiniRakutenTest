package com.rakutentest.android.ui.UIEvent.Event

import android.content.Context

sealed class ProductEvent {
    data class GetRemoteProducts(val app: Context, val keyWord: String): ProductEvent()

    data class GetRemoteProductDetails(val app: Context, val id: Int): ProductEvent()

    object GetLocalProducts: ProductEvent()

    object DeleteLocalProductsUseCase: ProductEvent()

    data class IsNetworkError(val errorMessage: String):  ProductEvent()
}
