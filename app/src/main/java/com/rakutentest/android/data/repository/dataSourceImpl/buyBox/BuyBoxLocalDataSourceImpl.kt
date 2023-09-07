package com.rakutentest.android.data.repository.dataSourceImpl.buyBox

import com.rakutentest.android.data.db.dao.BuyBoxDAO
import com.rakutentest.android.data.model.dataLocal.BuyboxRoom
import com.rakutentest.android.data.repository.dataSource.buyBox.BuyBoxLocalDataSource
import kotlinx.coroutines.flow.Flow

class BuyBoxLocalDataSourceImpl(
    private val buyBoxDAO: BuyBoxDAO
) : BuyBoxLocalDataSource{
    override fun getLocalBuyBox(productId: Int): Flow<BuyboxRoom> {
       return  buyBoxDAO.getBuyBoxForProductId(productId = productId)
    }
}