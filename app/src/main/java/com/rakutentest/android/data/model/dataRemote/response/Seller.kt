package com.rakutentest.android.data.model.dataRemote.response

import com.google.gson.annotations.SerializedName

data class Seller(
    @SerializedName("id")
    val id: Int,
    @SerializedName("login")
    val login: String
)
