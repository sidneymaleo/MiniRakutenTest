package com.rakutentest.android.data.apiService

import com.google.common.truth.Truth.assertThat
import com.rakutentest.android.BuildConfig
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ProductAPIServiceTest {

    private lateinit var service: ProductAPIService
    //we initialize our mock server
    private lateinit var server: MockWebServer

    @Before
    fun setUp() {
        //we call our mock web server constructor
        server = MockWebServer()
        service = Retrofit.Builder()
            .baseUrl(server.url(""))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ProductAPIService::class.java)
    }

    /**
     * We create our enqueue Mock Response
     * This function help us to learn our
     * json resources files
     */
    private fun enqueueMockResponse(
        fileName: String
    ) {
        val inputStream = javaClass.classLoader!!.getResourceAsStream(fileName)
        val source = inputStream.source().buffer()
        val mockResponse = MockResponse()
        mockResponse.setBody(source.readString(Charsets.UTF_8))
        server.enqueue(mockResponse)
    }

    @Test
    fun `Retrieve a product list`() {
        runBlocking {
            //we load our json response
            enqueueMockResponse("productsresponse.json")
            //we simulate our api call
            val responseBody = service.getProducts(keyWord = "Samsung").body()
            val request = server.takeRequest()
            //we test if our response is Not null
            assertThat(responseBody).isNotNull()
            assertThat(request.path).isEqualTo("/products/search?keyword=Samsung")
            //we test our product list response length
            assertThat(responseBody?.products!!.size).isEqualTo(20)
        }
    }

    @Test
    fun `Retrieve a product details`() {
        runBlocking {
            //we load our json response
            enqueueMockResponse("productdetailsresponse.json")
            //we simulate our api call
            val responseBody = service.getProducDetails(id = 6035914280).body()
            val request = server.takeRequest()
            assertThat(responseBody).isNotNull()
            assertThat(request.path).isEqualTo("/products/details?id=6035914280")
        }
    }
}