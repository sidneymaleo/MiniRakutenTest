package com.rakutentest.android.data.repository

import com.rakutentest.android.data.model.dataLocal.BuyboxRoom
import com.rakutentest.android.data.repository.dataSource.buyBox.BuyBoxLocalDataSource
import com.rakutentest.android.domain.repository.BuyBoxRepository
import kotlinx.coroutines.flow.Flow

class BuyBoxRepositoryImpl(
    private val buyBoxLocalDataSource: BuyBoxLocalDataSource
): BuyBoxRepository {
    override fun getBuyBox(productId: Int): Flow<BuyboxRoom> {
        return buyBoxLocalDataSource.getLocalBuyBox(productId = productId)
    }
}