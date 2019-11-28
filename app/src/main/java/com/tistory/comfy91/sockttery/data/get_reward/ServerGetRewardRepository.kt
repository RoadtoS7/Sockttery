package com.tistory.comfy91.sockttery.data.get_reward

import retrofit2.Call
import com.tistory.comfy91.sockttery.api.ServerService

class ServerGetRewardRepository: GetRewardRepository{
    override fun reqGetReward(userIdx: String, money: String): Call<ResGetReward> {
        return ServerService.service.reqGetReward(userIdx, money)
    }

}