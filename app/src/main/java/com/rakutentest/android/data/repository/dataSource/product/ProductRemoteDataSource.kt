package com.rakutentest.android.data.repository.dataSource.product

import com.rakutentest.android.data.model.dataRemote.response.ProductDetails
import retrofit2.Response
import com.rakutentest.android.data.model.dataRemote.response.Products

interface ProductRemoteDataSource {

    suspend fun getProducts(
        keyWord: String
    ): Response<Products>

    suspend fun getProductDetails(
        id: Int
    ): Response<ProductDetails>
}