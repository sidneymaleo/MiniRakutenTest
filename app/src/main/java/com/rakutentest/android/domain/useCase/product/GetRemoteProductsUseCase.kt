package com.rakutentest.android.domain.useCase.product

import com.rakutentest.android.data.model.dataRemote.response.ProductList
import com.rakutentest.android.data.util.Resource
import com.rakutentest.android.domain.repository.ProductRepository

class GetRemoteProductsUseCase(private val productRepository: ProductRepository) {

    suspend fun execute(keyWord: String): Resource<ProductList> {
        return productRepository.getProducts(keyWord = keyWord)
    }
}