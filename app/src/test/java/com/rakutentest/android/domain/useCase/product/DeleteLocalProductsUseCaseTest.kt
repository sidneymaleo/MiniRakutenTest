package com.rakutentest.android.domain.useCase.product

import com.google.common.truth.Truth
import com.rakutentest.android.domain.repository.FakeProductRepository
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*

import org.junit.Before
import org.junit.Test

class DeleteLocalProductsUseCaseTest {

    private lateinit var fakeProductRepository: FakeProductRepository
    private lateinit var deleteLocalProductUseCase: DeleteLocalProductsUseCase


    @Before
    fun setUp() {
        fakeProductRepository = FakeProductRepository()
        deleteLocalProductUseCase = DeleteLocalProductsUseCase(productRepository = fakeProductRepository)
    }

    @Test
    fun `retrieve all local products`() = runTest {
        deleteLocalProductUseCase.execute()
        Truth.assertThat(fakeProductRepository.productRooms.size).isEqualTo(0)
    }

}