package com.tistory.comfy91.sockttery.data.charge


import retrofit2.Call

interface ChargeRepository {
    fun reqCharge(userIdx: String ,chargeMoney: String): Call<ResCharge>
}