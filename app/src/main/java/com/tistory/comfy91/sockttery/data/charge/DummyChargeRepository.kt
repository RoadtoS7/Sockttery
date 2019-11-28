package com.tistory.comfy91.sockttery.data.charge


import retrofit2.Call
import retrofit2.mock.Calls

class DummyChargeRepository : ChargeRepository{
    override fun reqCharge(userIdx: String, chargeMoney: String): Call<ResCharge> {
        return Calls.response(
            ResCharge(
                success = true,
                message = "Return Dummy Data Success"
            )
        )
    }

}