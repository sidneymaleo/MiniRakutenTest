package com.rakutentest.android.data.db.dao

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import app.cash.turbine.test
import com.google.common.truth.Truth
import com.rakutentest.android.data.db.RakutenDatabase
import com.rakutentest.android.data.model.dataLocal.BuyboxRoom
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
class BuyBoxDAOTest {

    private lateinit var database: RakutenDatabase
    private lateinit var dao: BuyBoxDAO
    private lateinit var productDAO: ProductDAO

    val buyBoxList = listOf(
        BuyboxRoom(
            id = 1,
            salePrice = 689.99f,
            advertType = "NEW",
            advertQuality = "NEW",
            saleCrossedPrice = 859f,
            salePercentDiscount = 19,
            isRefurbished = false,
            productId = 6035914280
        ),
        BuyboxRoom(
            id = 23,
            salePrice = 689.89f,
            advertType = "NEW",
            advertQuality = "NEW",
            saleCrossedPrice = 860f,
            salePercentDiscount = 20,
            isRefurbished = false,
            productId = 6035914281
        )
    )

    val productList = listOf(
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

        dao = database.getBuyBoxDAO()
        productDAO = database.getProductDAO()
    }

    /**
     * This method insert our buyBox
     * and should be used in our tests
     */
    fun insertBuyBox() = runTest {
        productList.forEach {productRoom ->
            productDAO.insert(product = productRoom)
        }

        dao.insert(
            BuyboxRoom(
                id = 23,
                salePrice = 689.89f,
                advertType = "NEW",
                advertQuality = "NEW",
                saleCrossedPrice = 860f,
                salePercentDiscount = 20,
                isRefurbished = false,
                productId = 6035914280
            )
        )
        dao.insert(
            BuyboxRoom(
                id = 1,
                salePrice = 689.99f,
                advertType = "NEW",
                advertQuality = "NEW",
                saleCrossedPrice = 859f,
                salePercentDiscount = 19,
                isRefurbished = false,
                productId = 5504650604
            )
        )
    }

    @Test
    fun get_all_buy_box_Test() = runTest {
        insertBuyBox()
        //now our buy box list
        dao.getAllBuyBox().test {
            //we get our flow item
            val buyBoxList = awaitItem()
            Truth.assertThat(buyBoxList.size).isEqualTo(2)
            Truth.assertThat(buyBoxList[0].id).isEqualTo(1)
            Truth.assertThat(buyBoxList[0].salePrice).isEqualTo(689.99f)
            Truth.assertThat(buyBoxList[0].advertType).isEqualTo("NEW")
            Truth.assertThat(buyBoxList[0].advertQuality).isEqualTo("NEW")
            Truth.assertThat(buyBoxList[0].saleCrossedPrice).isEqualTo(859f)
            Truth.assertThat(buyBoxList[0].saleCrossedPrice).isEqualTo(859.0f)
            Truth.assertThat(buyBoxList[0].productId).isEqualTo(5504650604)
            cancel()
        }
    }

    @Test
    fun delete_all_buy_box() = runTest {
        //we insert our buy box before to delete it
        insertBuyBox()
        //we test we have really two item
        dao.getAllBuyBox().test {
            //we get our flow item
            val products = awaitItem()
            Truth.assertThat(products.size).isEqualTo(2)
            cancel()
        }
        //we delete our all product
        dao.deleteAllBuyBox()
        //we test if we have 0 element in our products list
        dao.getAllBuyBox().test {
            //we get our flow item
            val buyboxList = awaitItem()
            Truth.assertThat(buyboxList.size).isEqualTo(0)
            cancel()
        }
    }

    //here we close our database
    @After
    fun cleanup() {
        database.close()
    }


}