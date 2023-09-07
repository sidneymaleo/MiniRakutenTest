package com.rakutentest.android.data.model.dataLocal

import androidx.room.*
import com.rakutentest.android.data.model.dataRemote.response.Product

@Entity(
    tableName = "product_data_table"
)
data class ProductRoom(
    @ColumnInfo(name = "product_id")
    @PrimaryKey(autoGenerate = false)
    var id: Int?,
    @ColumnInfo("product_newBestPrice")
    var newBestPrice: Float,
    @ColumnInfo("product_usedBestPrice")
    var usedBestPrice: Float,
    @ColumnInfo("product_headline")
    var headline: String,
    @ColumnInfo("product_reviewsAverageNote")
    var reviewsAverageNote: Float,
    @ColumnInfo("product_nbReviews")
    val nbReviews: Int,
    @ColumnInfo("product_categoryRef")
    var categoryRef: Int,
    @ColumnInfo("product_imagesUrls")
    val imagesUrls: List<String>
)
