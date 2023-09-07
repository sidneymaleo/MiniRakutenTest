package com.rakutentest.android.data.apiService

import com.rakutentest.android.data.model.dataRemote.response.Products
import retrofit2.Response
import retrofit2.http.Query
import retrofit2.http.GET

interface ProductService {

    @GET("/api/address/search")
    suspend fun getProducts(
        @Query("keyword") keyWord : String
    ): Response<Products>
}