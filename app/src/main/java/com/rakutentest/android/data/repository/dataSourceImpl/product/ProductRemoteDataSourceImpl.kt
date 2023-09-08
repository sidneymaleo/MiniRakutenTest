package com.rakutentest.android.data.repository.dataSourceImpl.product

import com.rakutentest.android.data.apiService.ProductAPIService
import com.rakutentest.android.data.model.dataRemote.response.ProductDetails
import com.rakutentest.android.data.model.dataRemote.response.ProductList
import com.rakutentest.android.data.repository.dataSource.product.ProductRemoteDataSource
import retrofit2.Response

class ProductRemoteDataSourceImpl(
    private val productAPIService: ProductAPIService
): ProductRemoteDataSource {
    override suspend fun getProducts(keyWord: String): Response<ProductList> {
       return productAPIService.getProducts(keyWord = keyWord)
    }

    override suspend fun getProductDetails(id: Long): Response<ProductDetails> {
        return productAPIService.getProducDetails(id = id)
    }
}