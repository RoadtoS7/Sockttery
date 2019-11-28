package com.tistory.comfy91.sockttery.data.my_page

import retrofit2.Call

interface    MyPageRepository{
    fun reqMyPage(userId : String) : Call<ResMyPage>
}