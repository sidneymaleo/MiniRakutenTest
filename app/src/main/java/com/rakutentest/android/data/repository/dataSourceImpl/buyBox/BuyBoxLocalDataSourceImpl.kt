package com.rakutentest.android.data.repository.dataSourceImpl.buyBox

import com.rakutentest.android.data.db.dao.BuyBoxDAO
import com.rakutentest.android.data.model.dataLocal.BuyboxRoom
import com.rakutentest.android.data.model.dataLocal.ProductRoom
import com.rakutentest.android.data.repository.dataSource.buyBox.BuyBoxLocalDataSource
import kotlinx.coroutines.flow.Flow

class BuyBoxLocalDataSourceImpl(
    private val buyBoxDAO: BuyBoxDAO
) : BuyBoxLocalDataSource{
    override fun getLocalBuyBox(productId: Long): Flow<BuyboxRoom> {
       return  buyBoxDAO.getBuyBoxForProductId(productId = productId)
    }

    override suspend fun insertBuyBox(buybox: BuyboxRoom) {
        buyBoxDAO.insert(buybox = buybox)
    }
}