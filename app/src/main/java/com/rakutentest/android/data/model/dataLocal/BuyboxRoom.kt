package com.rakutentest.android.data.model.dataLocal

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "buybox_data_table",
    foreignKeys = [
        ForeignKey(
            entity = ProductRoom::class,
            parentColumns = ["product_id"],
            childColumns = ["buybox_productId"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.NO_ACTION)
    ]
)
data class BuyboxRoom(
    @ColumnInfo("buybox_id")
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    @ColumnInfo("buybox_salePrice")
    var salePrice: Float,
    @ColumnInfo("buybox_advertType")
    var advertType: String,
    @ColumnInfo("buybox_advertQuality")
    var advertQuality: String,
    @ColumnInfo("buybox_saleCrossedPrice")
    var saleCrossedPrice: Float,
    @ColumnInfo("buybox_salePercentDiscount")
    var salePercentDiscount: Int,
    @ColumnInfo("buybox_isRefurbished")
    var isRefurbished: Boolean,
    @ColumnInfo("buybox_productId")
    var productId: Int
)
