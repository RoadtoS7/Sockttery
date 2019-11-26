package com.tistory.comfy91.sockttery.data

import com.google.gson.annotations.SerializedName

data class ResSignUp (
    val status: String,
    val success: Boolean,
    val message: String,
    @SerializedName("data")
    val userIdx: Int
)

