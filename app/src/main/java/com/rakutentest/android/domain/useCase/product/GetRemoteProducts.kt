package com.rakutentest.android.domain.useCase.product

import com.rakutentest.android.data.model.dataRemote.response.Products
import com.rakutentest.android.data.util.Resource
import com.rakutentest.android.domain.repository.ProductRepository

class GetRemoteProducts(private val productRepository: ProductRepository) {

    suspend fun execute(keyWord: String): Resource<Products> {
        return productRepository.getProducts(keyWord = keyWord)
    }
}