package com.rakutentest.android.domain.repository

import com.rakutentest.android.data.model.dataLocal.BuyboxRoom
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeBuyBoxRepository: BuyBoxRepository {

    override fun getBuyBox(productId: Long): Flow<BuyboxRoom> {
        return flow {
            emit(
                BuyboxRoom(
                    id = 1,
                    salePrice = 689.99f,
                    advertType = "NEW",
                    advertQuality = "NEW",
                    saleCrossedPrice = 859f,
                    salePercentDiscount = 19,
                    isRefurbished = false,
                    productId = 6035914280
                )
            )
        }
    }


    override suspend fun insertBuyBox(buyBox: BuyboxRoom) {
    }

}