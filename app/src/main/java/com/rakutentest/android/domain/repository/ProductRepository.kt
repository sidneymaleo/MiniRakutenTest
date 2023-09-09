package com.rakutentest.android.domain.repository

import com.rakutentest.android.data.model.dataLocal.ProductRoom
import com.rakutentest.android.data.model.dataRemote.response.ProductDetails
import com.rakutentest.android.data.model.dataRemote.response.ProductList
import com.rakutentest.android.data.util.Resource
import kotlinx.coroutines.flow.Flow

interface ProductRepository {

    suspend fun getProducts(
        keyWord: String
    ): Resource<ProductList>

    suspend fun getProductDetails(
        id: Long
    ): Resource<ProductDetails>

    fun getLocalProducts(): Flow<List<ProductRoom>>

    suspend fun deleteProducts()
}