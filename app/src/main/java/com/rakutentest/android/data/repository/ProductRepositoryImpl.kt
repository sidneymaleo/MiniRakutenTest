package com.rakutentest.android.data.repository

import com.rakutentest.android.data.model.dataLocal.ProductRoom
import com.rakutentest.android.data.model.dataRemote.response.ProductDetails
import com.rakutentest.android.data.model.dataRemote.response.ProductList
import com.rakutentest.android.data.repository.dataSource.product.ProductLocalDataSource
import com.rakutentest.android.data.repository.dataSource.product.ProductRemoteDataSource
import com.rakutentest.android.data.util.Resource
import com.rakutentest.android.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

class ProductRepositoryImpl(
    private val productLocalDataSource: ProductLocalDataSource,
    private val productRemoteDataSource: ProductRemoteDataSource
) : ProductRepository{

    /**
     * This method convert response to ressource products
     */
    private fun responseToResourceProducts(response: Response<ProductList>): Resource<ProductList> {
        if (response.isSuccessful) {
            response.body()?.let { result ->
                return Resource.Success(result)
            }
        }
        return Resource.Error(response.message())
    }

    /**
     * This method convert response to ressource products details
     */
    private fun responseToResourceProductDetails(response: Response<ProductDetails>): Resource<ProductDetails> {
        if (response.isSuccessful) {
            response.body()?.let { result ->
                return Resource.Success(result)
            }
        }
        return Resource.Error(response.message())
    }
    override suspend fun getProducts(keyWord: String): Resource<ProductList> {
        return responseToResourceProducts(
            productRemoteDataSource.getProducts(
                keyWord = keyWord
            )
        )
    }



    override suspend fun getProductDetails(id: Int): Resource<ProductDetails> {
       return responseToResourceProductDetails(
           productRemoteDataSource.getProductDetails(
               id = id
           )
       )
    }

    override fun getLocalProducts(): Flow<List<ProductRoom>> {
        return productLocalDataSource.getLocalProducts()
    }

    override suspend fun deleteProducts() {
        productLocalDataSource.deleteLocalProducts()
    }
}