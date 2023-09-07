package com.rakutentest.android.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.rakutentest.android.data.db.dao.BuyBoxDAO
import com.rakutentest.android.data.db.dao.ProductDAO
import com.rakutentest.android.data.model.dataLocal.BuyboxRoom
import com.rakutentest.android.data.model.dataLocal.ProductRoom

@Database(
    entities = [
        ProductRoom::class,
        BuyboxRoom::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class RakutenDatabase : RoomDatabase() {

    abstract fun getProductDAO(): ProductDAO

    abstract fun getBuyBoxDAO(): BuyBoxDAO

}