package com.rakutentest.android.domain.useCase.buybox

import app.cash.turbine.test
import com.google.common.truth.Truth
import com.rakutentest.android.domain.repository.FakeBuyBoxRepository
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*

import org.junit.Before
import org.junit.Test

class GetLocalBuyBoxUseCaseTest {

    private lateinit var getLocalBuyBoxUseCase: GetLocalBuyBoxUseCase
    private lateinit var fakeBuyBoxRepository: FakeBuyBoxRepository

    @Before
    fun setUp() {
        fakeBuyBoxRepository = FakeBuyBoxRepository()
        getLocalBuyBoxUseCase = GetLocalBuyBoxUseCase(
            buyBoxRepository = fakeBuyBoxRepository
        )
    }

    /**
     * we test if we retrieve local buyBox
     */
    @Test
    fun `we retrieve our buy box`() = runTest {
        getLocalBuyBoxUseCase.execute(productId = 123423).test {
            val buyBoxList = expectMostRecentItem()
            Truth.assertThat(buyBoxList.id).isEqualTo(1)
            Truth.assertThat(buyBoxList.salePrice).isEqualTo(689.99f)
            Truth.assertThat(buyBoxList.advertType).isEqualTo("NEW")
            Truth.assertThat(buyBoxList.advertQuality).isEqualTo("NEW")
            Truth.assertThat(buyBoxList.saleCrossedPrice).isEqualTo(859f)
            Truth.assertThat(buyBoxList.salePercentDiscount).isEqualTo(19)
            Truth.assertThat(buyBoxList.isRefurbished).isEqualTo(false)
            Truth.assertThat(buyBoxList.productId).isEqualTo(6035914280)
        }
    }
}