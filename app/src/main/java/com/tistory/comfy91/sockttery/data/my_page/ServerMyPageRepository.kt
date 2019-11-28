package com.tistory.comfy91.sockttery.data.my_page

import com.tistory.comfy91.sockttery.api.ServerService
import retrofit2.Call

class ServerMyPageRepository : MyPageRepository {
    override fun reqMyPage(userId: String): Call<ResMyPage> {
        return ServerService.service.reqMyPage(userId)
    }

}