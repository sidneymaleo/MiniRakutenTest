package com.rakutentest.android.domain.useCase.buybox

import com.rakutentest.android.data.model.dataLocal.BuyboxRoom
import com.rakutentest.android.domain.repository.BuyBoxRepository

class SaveBuyBoxUseCase(private val buyBoxRepository: BuyBoxRepository) {
    suspend fun execute(buyBox: BuyboxRoom) = buyBoxRepository.insertBuyBox(buyBox = buyBox)
}