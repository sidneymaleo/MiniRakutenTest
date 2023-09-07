package com.rakutentest.android.data.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.rakutentest.android.data.model.dataLocal.BuyboxRoom
import kotlinx.coroutines.flow.Flow

@Dao
interface BuyBoxDAO {

    @Query("""SELECT * FROM buybox_data_table
        INNER JOIN buybox_data_table on buybox_data_table.buybox_productId = buybox_data_table.buybox_id
        INNER JOIN product_data_table ON product_data_table.product_id = buybox_data_table.buybox_productId
        WHERE product_data_table.product_id = :productId
    """)
    fun getBuyBoxForProductId(productId: Int): Flow<BuyboxRoom>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(buybox: BuyboxRoom)

    @Query("SELECT * FROM buybox_data_table")
    fun getAllBuyBox(): Flow<BuyboxRoom>

    @Query("DELETE  FROM buybox_data_table")
    suspend fun deleteAllBuyBox()

    @Delete
    suspend fun deleteBuyBox(buybox: BuyboxRoom)

    @Update
    suspend fun updateBuyBox(buybox: BuyboxRoom)
}