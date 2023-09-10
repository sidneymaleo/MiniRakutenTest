package com.rakutentest.android.domain.useCase.product

import com.rakutentest.android.data.model.dataLocal.ProductRoom
import com.rakutentest.android.domain.repository.ProductRepository

class SaveProductUseCase(private val productRepository: ProductRepository)  {
    suspend fun execute(product: ProductRoom) = productRepository.insertProduct(product = product)
}