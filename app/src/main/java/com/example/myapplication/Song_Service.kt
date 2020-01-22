package com.example.myapplication

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Song_Service{
    @GET("findLyrics/?")
    fun getSong(@Query("q") q : String,@Query("api_token") api_token: String) : Call<Result>
}