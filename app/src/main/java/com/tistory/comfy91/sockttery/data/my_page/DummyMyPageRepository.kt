package com.tistory.comfy91.sockttery.data.my_page

import retrofit2.Call
import retrofit2.mock.Calls

class DummyMyPageRepository: MyPageRepository {
    override fun reqMyPage(userId: String): Call<ResMyPage> {
        return Calls.response(
            ResMyPage(
                success = true,
                message = "통신에 성공했습니다.",
                data = MyData(
                    0,
                    "Merry1225",
                    "1234",
                    "10000",
                    0
                )
            )
        )
    }

}