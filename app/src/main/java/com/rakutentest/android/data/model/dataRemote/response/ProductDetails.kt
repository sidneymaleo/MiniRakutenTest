package com.rakutentest.android.data.model.dataRemote.response

import com.google.gson.annotations.SerializedName

data class ProductDetails (
    @SerializedName("productId")
    val productId: Int,
    @SerializedName("salePrice")
    val salePrice: Float,
    @SerializedName("newBestPrice")
    val newBestPrice: Float,
    @SerializedName("usedBestPrice")
    val usedBestPrice: Float,
    @SerializedName("seller")
    val seller: Seller,
    @SerializedName("quality")
    val quality: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("sellerComment")
    val sellerComment: String,
    @SerializedName("headline")
    val headline: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("categories")
    val categories: List<String>,
    @SerializedName("globalRating")
    val globalRating: GlobalRating,
    @SerializedName("images")
    val images: List<Image>


)