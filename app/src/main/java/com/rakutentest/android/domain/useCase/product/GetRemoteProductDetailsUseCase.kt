package com.rakutentest.android.domain.useCase.product

import com.rakutentest.android.data.model.dataRemote.response.ProductDetails
import com.rakutentest.android.data.util.Resource
import com.rakutentest.android.domain.repository.ProductRepository

class GetRemoteProductDetailsUseCase(private val productRepository: ProductRepository) {
    suspend fun execute(id: Long): Resource<ProductDetails> {
        return productRepository.getProductDetails(id = id)
    }
}