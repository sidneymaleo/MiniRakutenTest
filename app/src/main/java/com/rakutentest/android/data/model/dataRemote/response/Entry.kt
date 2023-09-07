package com.rakutentest.android.data.model.dataRemote.response

import com.google.gson.annotations.SerializedName

data class Entry(
    @SerializedName("size")
    val size: String,
    @SerializedName("url")
    val url: String
)
