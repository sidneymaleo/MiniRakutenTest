package com.rakutentest.android.domain.useCase.product

import com.google.common.truth.Truth
import com.rakutentest.android.domain.repository.FakeProductRepository
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*

import org.junit.Before
import org.junit.Test

class GetRemoteProductDetailsUseCaseTest {

    private lateinit var getRemoteProductDetailsUseCase: GetRemoteProductDetailsUseCase
    private lateinit var fakeProductRepository: FakeProductRepository

    @Before
    fun setUp() {
        fakeProductRepository = FakeProductRepository()
        getRemoteProductDetailsUseCase = GetRemoteProductDetailsUseCase(
            productRepository = fakeProductRepository
        )
    }

    /**
     * we test if we retrieve product details
     */
    @Test
    fun `retrieve product details`() = runTest {
        val productDetails = getRemoteProductDetailsUseCase.execute(id = 6035914280).data
        Truth.assertThat(productDetails?.headline).isEqualTo("Samsung Galaxy S21 5G 128 Go Double SIM Violet")
        Truth.assertThat(productDetails?.productId).isEqualTo(6035914280)
        Truth.assertThat(productDetails?.salePrice).isEqualTo(689.99f)
        Truth.assertThat(productDetails?.newBestPrice).isEqualTo(689.99f)
        Truth.assertThat(productDetails?.usedBestPrice).isEqualTo(640f)
    }
}