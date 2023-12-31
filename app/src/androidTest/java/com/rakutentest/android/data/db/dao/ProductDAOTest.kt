package com.rakutentest.android.data.db.dao

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import app.cash.turbine.test
import com.google.common.truth.Truth
import com.rakutentest.android.data.db.RakutenDatabase
import com.rakutentest.android.data.model.dataLocal.ProductRoom
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@SmallTest
class ProductDAOTest {

    private lateinit var dao: ProductDAO
    private lateinit var database: RakutenDatabase

    // we initialize our room data product list
    val products = listOf(
        ProductRoom(
            id = 6035914280,
            newBestPrice = 689.99f,
            usedBestPrice = 640f,
            headline = "Samsung Galaxy S21 5G 128 Go Double SIM Violet",
            reviewsAverageNote = 4.627659574468085,
            nbReviews = 94,
            categoryRef = 194695,
            imagesUrls = listOf(
                "https://fr.shopping.rakuten.com/photo/1673299896.jpg"
            )
        ),
        ProductRoom(
            id = 5504650604,
            newBestPrice = 475.95f,
            usedBestPrice = 415f,
            headline = "Samsung Galaxy S20 FE 5G 128 Go 6.7 pouces Bleu Double Sim",
            reviewsAverageNote = 4.570707070707071,
            nbReviews = 198,
            categoryRef = 194695,
            imagesUrls = listOf(
                "https://fr.shopping.rakuten.com/photo/1511640339.jpg"
            )
        )
    )


    //we initialize our field
    @Before
    fun setUp() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            RakutenDatabase::class.java
        ).build()
        dao = database.getProductDAO()
    }


    /**
     * This method insert our product list
     * and should be used in our tests
     */
    fun insertProductList() = runTest {
        products.forEach {productRoom ->
            dao.insert(product = productRoom)
        }
    }



    //here we close our database
    @After
    fun cleanup() {
        database.close()
    }

    @Test
    fun get_all_database_products_Test() = runTest {
        //before we insert our product data
        insertProductList()

        //now we  our get all products method to test it
        dao.getAllProduct().test {
            //we get our flow item
            val products = awaitItem()
            Truth.assertThat(products.size).isEqualTo(2)
            //we test our second product list headline item
            Truth.assertThat(products[1].headline).isEqualTo("Samsung Galaxy S21 5G 128 Go Double SIM Violet")
            cancel()
        }
    }
    
    @Test
    fun delete_all_products_Test() = runTest {
        //we insert our products before to delete it
        insertProductList()

        //we test we have really two item
        dao.getAllProduct().test {
            //we get our flow item
            val products = awaitItem()
            Truth.assertThat(products.size).isEqualTo(2)
            cancel()
        }
        //we delete our all product
        dao.deleteAllProduct()
        //we test if we have 0 element in our products list
        dao.getAllProduct().test {
            //we get our flow item
            val products = awaitItem()
            Truth.assertThat(products.size).isEqualTo(0)
            cancel()
        }
    }

    @Test
    fun update_product_Test() = runTest {
        //we insert our products before to update it
        insertProductList()


        /**
         *  now we  our get all products method to test it
         *  we test our second product list headline item before the update
         */
        dao.getAllProduct().test {
            //we get our flow item
            val products = awaitItem()
            //we test our second product list headline item
            Truth.assertThat(products[1].headline).isEqualTo("Samsung Galaxy S21 5G 128 Go Double SIM Violet")
            cancel()
        }


        // now we update our second products headline
        //products[1].headline = "Samsung Galaxy S21 5G 128 Go Double SIM Violet Sidney of rakuten set this headline"
        dao.updateProduct(ProductRoom(
            id = 6035914280,
            newBestPrice = 689.99f,
            usedBestPrice = 640f,
            headline = "Samsung Galaxy S21 5G 128 Go Double SIM Violet Sidney of rakuten set this headline",
            reviewsAverageNote = 4.627659574468085,
            nbReviews = 94,
            categoryRef = 194695,
            imagesUrls = listOf(
                "https://fr.shopping.rakuten.com/photo/1673299896.jpg"
            )
        ))

        dao.getAllProduct().test {
            //we get our flow item
            val products = awaitItem()
            //we test our second product list headline item
            // to verify if our update was be a success
            Truth.assertThat(products[1].headline).isEqualTo("Samsung Galaxy S21 5G 128 Go Double SIM Violet Sidney of rakuten set this headline")
            cancel()
        }
    }

}