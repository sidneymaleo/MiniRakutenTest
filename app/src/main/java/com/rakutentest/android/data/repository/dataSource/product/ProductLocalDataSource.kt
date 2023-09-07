package com.rakutentest.android.data.repository.dataSource.product

import com.rakutentest.android.data.model.dataLocal.ProductRoom
import kotlinx.coroutines.flow.Flow

interface ProductLocalDataSource {

   fun getLocalProducts(): Flow<List<ProductRoom>>

   suspend fun deleteLocalProducts()
}