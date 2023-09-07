package com.rakutentest.android.data.model.dataRemote.response

import com.google.gson.annotations.SerializedName

data class GlobalRating(
    @SerializedName("score")
    val score: Float,
    @SerializedName("nbReviews")
    val nbReviews: Int
)
