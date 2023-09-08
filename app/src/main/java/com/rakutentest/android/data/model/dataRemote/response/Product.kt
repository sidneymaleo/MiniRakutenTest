package com.rakutentest.android.data.model.dataRemote.response

import com.google.gson.annotations.SerializedName

data class Product(
    @SerializedName("id")
    val id: Float,
    @SerializedName("newBestPrice")
    val newBestPrice: Float,
    @SerializedName("usedBestPrice")
    val usedBestPrice: Float,
    @SerializedName("headline")
    val headline: String,
    @SerializedName("reviewsAverageNote")
    val reviewsAverageNote: Double,
    @SerializedName("nbReviews")
    val nbReviews: Int,
    @SerializedName("categoryRef")
    val categoryRef: Int,
    @SerializedName("imagesUrls")
    val imagesUrls: List<String>,
    @SerializedName("buybox")
    val buybox: Buybox
)
