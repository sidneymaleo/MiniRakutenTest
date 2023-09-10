package com.rakutentest.android.domain.useCase.product

import app.cash.turbine.test
import com.google.common.truth.Truth
import com.rakutentest.android.domain.repository.FakeProductRepository
import org.junit.Assert.*
import org.junit.Before
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test


@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class GetLocalProductsUseCaseTest {

    private lateinit var getLocalProductsUseCase: GetLocalProductsUseCase
    private lateinit var fakeProductRepository: FakeProductRepository

    @Before
    fun setUp() {
        fakeProductRepository = FakeProductRepository()
        getLocalProductsUseCase = GetLocalProductsUseCase(productRepository = fakeProductRepository)
    }

    @Test
    fun `retrieve all local products`() = runTest {
        //we test if our product list retrieve good list
         getLocalProductsUseCase.execute().test {
             val localProducts = expectMostRecentItem()
             Truth.assertThat(localProducts.size).isEqualTo(2)
             Truth.assertThat(localProducts[0].categoryRef).isEqualTo(194695)
        }

    }
}