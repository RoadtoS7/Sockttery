package com.tistory.comfy91.sockttery.data.get_reward

import retrofit2.Call
import retrofit2.mock.Calls

class DummyGetRewardRepository: GetRewardRepository{
    override fun reqGetReward(userIdx: String, money: String): Call<ResGetReward> {
        return Calls.response(
            ResGetReward( true, "Dummy  데이터 수신 성공")
        )
    }

}