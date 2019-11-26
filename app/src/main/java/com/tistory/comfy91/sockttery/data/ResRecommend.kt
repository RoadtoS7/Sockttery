package com.tistory.comfy91.sockttery.data

data class ResRecommend(
    val success: Boolean,
    val data: PickedUser
)

data class PickedUser(
    val userIdx: Int,
    val userId: String
)