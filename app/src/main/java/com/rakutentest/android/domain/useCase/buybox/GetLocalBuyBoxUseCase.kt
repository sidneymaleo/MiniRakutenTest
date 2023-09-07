package com.rakutentest.android.domain.useCase.buybox

import com.rakutentest.android.data.model.dataLocal.BuyboxRoom
import com.rakutentest.android.domain.repository.BuyBoxRepository
import kotlinx.coroutines.flow.Flow

class GetLocalBuyBoxUseCase(private val buyBoxRepository: BuyBoxRepository) {
    fun execute(productId: Int): Flow<BuyboxRoom> {
        return buyBoxRepository.getBuyBox(productId = productId)
    }
}