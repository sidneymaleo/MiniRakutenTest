package com.rakutentest.android.data.repository.dataSourceImpl.product

import com.rakutentest.android.data.db.dao.ProductDAO
import com.rakutentest.android.data.model.dataLocal.ProductRoom
import com.rakutentest.android.data.repository.dataSource.product.ProductLocalDataSource
import kotlinx.coroutines.flow.Flow

class ProductLocalDataSourceImpl(
    private val productDAO: ProductDAO
) : ProductLocalDataSource {
    override fun getLocalProducts(): Flow<List<ProductRoom>> {
        return productDAO.getAllProduct()
    }

    override suspend fun deleteLocalProducts() {
        productDAO.deleteAllProduct()
    }


}