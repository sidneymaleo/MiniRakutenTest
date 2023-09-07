package com.rakutentest.android.data.repository.dataSourceImpl.product

import com.rakutentest.android.data.apiService.ProductService
import com.rakutentest.android.data.model.dataRemote.response.Products
import com.rakutentest.android.data.repository.dataSource.product.ProductRemoteDataSource
import retrofit2.Response

class ProductRemoteDataSourceImpl(
    private val productAPIService: ProductService
): ProductRemoteDataSource {
    override suspend fun getProducts(keyword: String): Response<Products> {
       return productAPIService.getProducts(keyword)
    }
}