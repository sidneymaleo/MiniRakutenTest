package com.rakutentest.android.data.repository.dataSource.buyBox

import com.rakutentest.android.data.model.dataLocal.BuyboxRoom
import com.rakutentest.android.data.model.dataLocal.ProductRoom
import kotlinx.coroutines.flow.Flow

interface BuyBoxLocalDataSource {
    fun getLocalBuyBox(productId: Int): Flow<BuyboxRoom>

    suspend fun insertBuyBox(buyBox: BuyboxRoom)
}