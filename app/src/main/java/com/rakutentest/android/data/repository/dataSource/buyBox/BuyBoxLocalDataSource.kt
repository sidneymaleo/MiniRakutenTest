package com.rakutentest.android.data.repository.dataSource.buyBox

import com.rakutentest.android.data.model.dataLocal.BuyboxRoom
import kotlinx.coroutines.flow.Flow

interface BuyBoxLocalDataSource {
    fun getLocalBuyBox(productId: Int): Flow<BuyboxRoom>
}