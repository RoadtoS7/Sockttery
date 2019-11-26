package com.tistory.comfy91.sockttery.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServerService {
    private const val BASE_URL = "http://52.79.100.159:3000"

    private val retrofit : Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val service : Service = retrofit.create(Service::class.java)
}