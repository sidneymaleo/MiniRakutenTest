package com.rakutentest.android.data.model.dataRemote.response

import com.google.gson.annotations.SerializedName

data class ImagesUrls(
    @SerializedName("entry")
    val entry: List<Entry>
)
