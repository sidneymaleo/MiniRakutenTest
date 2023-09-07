package com.rakutentest.android.domain.useCase.product

import com.rakutentest.android.domain.repository.ProductRepository

class DeleteLocalProducts(private val productRepository: ProductRepository) {
    suspend fun execute() = productRepository.deleteProducts()
}