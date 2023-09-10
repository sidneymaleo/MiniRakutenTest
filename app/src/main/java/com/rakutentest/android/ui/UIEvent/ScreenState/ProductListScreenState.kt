package com.rakutentest.android.ui.UIEvent.ScreenState

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.rakutentest.android.data.model.dataLocal.ProductRoom
import com.rakutentest.android.data.model.dataRemote.response.Product

/**
 * Here we initialize our productList screen attributes
 * this entity help our to observe the state of our product details screen
 */
data class ProductListScreenState (
    var isNetworkConnected: Boolean = true,
    var isNetworkError: Boolean = false,
    var productList : SnapshotStateList<Product> = mutableStateListOf(),
    var productRoomList: List<ProductRoom> = mutableListOf(),
    var isLoad: Boolean = false,
    var isRequested: Boolean = true,
    var isInternalError: Boolean = false
)