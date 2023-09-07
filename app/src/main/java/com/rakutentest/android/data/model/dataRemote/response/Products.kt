package com.rakutentest.android.data.model.dataRemote.response

import com.google.gson.annotations.SerializedName

data class Products(
    @SerializedName("totalResultProductsCount")
    val totalResultProductsCount: Int,
    @SerializedName("resultProductsCount")
    val resultProductsCount: Int,
    @SerializedName("pageNumber")
    val pageNumber: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("maxProductsPerPage")
    val maxProductsPerPage: Int,
    @SerializedName("maxPageNumber")
    val maxPageNumber: Int,
    @SerializedName("products")
    val products: List<Product>
)
