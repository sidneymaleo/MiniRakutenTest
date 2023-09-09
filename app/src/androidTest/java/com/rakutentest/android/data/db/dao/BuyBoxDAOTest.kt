package com.rakutentest.android.data.db.dao

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.rakutentest.android.data.db.RakutenDatabase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.After
import org.junit.Before
import org.junit.runner.RunWith


@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@SmallTest
class BuyBoxDAOTest {

    private lateinit var database: RakutenDatabase
    private lateinit var dao: BuyBoxDAO

    //we initialize our field
    @Before
    fun setUp() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            RakutenDatabase::class.java
        ).build()

        dao = database.getBuyBoxDAO()
    }

    //here we close our database
    @After
    fun cleanup() {
        database.close()
    }
}