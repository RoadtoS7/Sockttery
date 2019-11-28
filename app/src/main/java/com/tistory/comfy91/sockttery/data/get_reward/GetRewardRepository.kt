package com.tistory.comfy91.sockttery.data.get_reward

import retrofit2.Call

interface GetRewardRepository {
    fun reqGetReward(userIdx: String, money: String): Call<ResGetReward>
}