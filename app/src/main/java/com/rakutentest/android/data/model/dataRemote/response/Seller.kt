package com.rakutentest.android.data.model.dataRemote.response

import com.google.gson.annotations.SerializedName

data class Seller(
    @SerializedName("id")
    val id: Long,
    @SerializedName("login")
    val login: String
)
