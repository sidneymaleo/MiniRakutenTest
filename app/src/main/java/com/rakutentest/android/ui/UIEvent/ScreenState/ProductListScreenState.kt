package com.rakutentest.android.ui.UIEvent.ScreenState

import com.rakutentest.android.data.model.dataLocal.ProductRoom
import com.rakutentest.android.data.model.dataRemote.response.Product

/**
 * Here we initialize our productList screen attributes
 */
data class ProductListScreenState (
    var isNetworkConnected: Boolean = false,
    var isNetworkError: Boolean = false,
    var productList : List<Product> = mutableListOf(),
    var productRoomList: List<ProductRoom> = mutableListOf()
)