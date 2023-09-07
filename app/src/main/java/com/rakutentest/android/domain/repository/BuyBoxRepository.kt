package com.rakutentest.android.domain.repository

import com.rakutentest.android.data.model.dataLocal.BuyboxRoom
import kotlinx.coroutines.flow.Flow

interface BuyBoxRepository {
    fun getBuyBox(productId: Int): Flow<BuyboxRoom>
}