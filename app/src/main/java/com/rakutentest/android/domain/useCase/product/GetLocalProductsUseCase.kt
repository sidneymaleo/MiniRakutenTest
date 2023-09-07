package com.rakutentest.android.domain.useCase.product

import com.rakutentest.android.data.model.dataLocal.ProductRoom
import com.rakutentest.android.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow

class GetLocalProductsUseCase(private val productRepository: ProductRepository) {
    fun execute(): Flow<List<ProductRoom>> {
        return productRepository.getLocalProducts()
    }
}