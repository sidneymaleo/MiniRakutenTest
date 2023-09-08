package com.rakutentest.android.ui.UIEvent.ScreenState

import com.rakutentest.android.data.model.dataRemote.response.ProductDetails

/**
 * Here we initialize our product details screen attributes
 * this entity help our to observe the state of our product details screen
 */

data class ProductDetailsScreenState (
    var isNetworkConnected: Boolean = false,
    var isNetworkError: Boolean = false,
    var productDetails: ProductDetails? = null,
    var isLoad: Boolean = false,
)