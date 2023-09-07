package com.rakutentest.android.data.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.rakutentest.android.data.model.dataLocal.ProductRoom
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(product: ProductRoom)

    @Query("SELECT * FROM product_data_table")
    fun getAllProduct(): Flow<ProductRoom>

    @Query("DELETE  FROM product_data_table")
    suspend fun deleteAllProduct()

    @Delete
    suspend fun deleteProduct(product: ProductRoom)

    @Update
    suspend fun updateProduct(product: ProductRoom)


}