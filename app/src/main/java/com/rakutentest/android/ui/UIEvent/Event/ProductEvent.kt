package com.rakutentest.android.ui.UIEvent.Event

import android.content.Context

sealed class ProductEvent {
    data class GetRemoteProducts(val app: Context, val keyWord: String): ProductEvent()

    data class GetRemoteProductDetails(val app: Context, val id: Long): ProductEvent()

    object GetLocalProducts: ProductEvent()

    object DeleteLocalProductsUseCase: ProductEvent()

    data class IsNetworkError(val errorMessage: String):  ProductEvent()

    data class IsNetworkConnected(val errorMessage: String): ProductEvent()

    data class IsInternalError(val errorMessage: String): ProductEvent()
}
