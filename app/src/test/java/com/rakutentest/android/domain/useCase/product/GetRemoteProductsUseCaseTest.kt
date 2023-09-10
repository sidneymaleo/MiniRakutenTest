package com.rakutentest.android.domain.useCase.product

import com.google.common.truth.Truth
import com.rakutentest.android.domain.repository.FakeProductRepository
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*

import org.junit.Before
import org.junit.Test

class GetRemoteProductsUseCaseTest {

    private lateinit var getRemoteProductsUseCase : GetRemoteProductsUseCase
    private lateinit var fakeProductRepository: FakeProductRepository

    @Before
    fun setUp() {
        fakeProductRepository = FakeProductRepository()
        getRemoteProductsUseCase = GetRemoteProductsUseCase(
            productRepository = fakeProductRepository
        )
    }

    /**
     * we test if we retrieve product list
     */
    @Test
    fun `we retrieve our products list`() = runTest {
        val products = getRemoteProductsUseCase.execute(keyWord = "Samsung").data
        //we test our products size
        Truth.assertThat(products?.products!!.size).isEqualTo(2)
        Truth.assertThat(products.products[0].nbReviews).isEqualTo(94)
        Truth.assertThat(products.products[0].categoryRef).isEqualTo(194695)
    }
}