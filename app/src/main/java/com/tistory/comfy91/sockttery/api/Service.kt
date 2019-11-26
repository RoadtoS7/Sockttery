package com.tistory.comfy91.sockttery.api

import com.tistory.comfy91.sockttery.data.*
import retrofit2.Call
import retrofit2.http.*

interface Service{
    // 추첨된 사람 요청
    @GET("/lottos/pick")
    fun reqRecommend(): Call<ResRecommend>

    // 충전하기
    @GET("/users/{userIdx}/charge/{chargeMoney}")
    fun reqCharge(
        @Path("userIdx") userIdx: String,
        @Path("chargeMoney") chargeMoney: String
    ): Call<ResCharge>

    // 마이페이지
    @GET("/users/{userId}")
    fun reqMyPage(
        @Path("userId") userId: String
    ): Call<ResMyPage>

    // 양말누르기
    @POST("/lottos/{userIdx}/pay")
    fun reqClickSocks(
        @Path("userIdx") userIdx: String
    ): Call<ResClickSocks>

    // 추첨된 사람 돈 가져가기
    @GET("/lottos/{userIdx}/getReward/{reward}")
    fun reqGetReward(
        @Path("userIdx") userIdx: String
    ): Call<ResGetReward>


    // 회원가입
    @FormUrlEncoded
    @POST("/signup")
    fun reqSignUp(
       @Field("userId") userId: String,
       @Field("password") password: String
    ): Call<ResSignUp>

    //  로그인
    @FormUrlEncoded
    @POST("/signin")
    fun resSignIn(
        @Field("userId") userId: String,
        @Field("password") password: String
    ): Call<ResSignIn>








}