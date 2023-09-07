package com.rakutentest.android.data.model.dataRemote.response

import com.google.gson.annotations.SerializedName

data class Buybox(
    @SerializedName("salePrice")
    val salePrice: Float,
    @SerializedName("advertType")
    val advertType: String,
    @SerializedName("advertQuality")
    val advertQuality: String,
    @SerializedName("saleCrossedPrice")
    val saleCrossedPrice: Float,
    @SerializedName("saleCrossedPrice")
    val salePercentDiscount: Int,
    @SerializedName("isRefurbished")
    val isRefurbished: Boolean
)
