package com.rakutentest.android.data.apiService

import com.rakutentest.android.data.model.dataRemote.response.ProductDetails
import com.rakutentest.android.data.model.dataRemote.response.ProductList
import retrofit2.Response
import retrofit2.http.Query
import retrofit2.http.GET

interface ProductAPIService {

    @GET("/products/search")
    suspend fun getProducts(
        @Query("keyword") keyWord : String
    ): Response<ProductList>

    @GET("/products/details")
    suspend fun getProducDetails(
        @Query("id") id: Int
    ): Response<ProductDetails>

}