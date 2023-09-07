package com.rakutentest.android.data.model.dataRemote.response

import com.google.gson.annotations.SerializedName

data class Image(
    @SerializedName("imagesUrls")
    val imagesUrls: ImagesUrls,
    @SerializedName("id")
    val id: Int
)
