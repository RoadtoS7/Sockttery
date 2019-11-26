package com.tistory.comfy91.sockttery.data

data class ResMyPage (
    val success: Boolean,
    val message: String,
    val data: MyData
)

data class MyData(
    val userIdx: Int,
    val userId: String,
    val password: String,
    val money: String,
    val pay: Int
)