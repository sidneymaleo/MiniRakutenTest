package com.rakutentest.android.domain.repository

import com.rakutentest.android.data.model.dataLocal.BuyboxRoom
import com.rakutentest.android.data.model.dataLocal.ProductRoom
import kotlinx.coroutines.flow.Flow

interface BuyBoxRepository {
    fun getBuyBox(productId: Long): Flow<BuyboxRoom>

    suspend fun insertBuyBox(buyBox: BuyboxRoom)
}