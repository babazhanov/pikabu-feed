package com.progtech.pikabufeed.models

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface FeedApi {
    @GET("feed.php")
    fun feeds(): Call<List<FeedItem>>

    @GET("story.php?")
    fun detail(@Query("id") id: Int): Call<FeedItem>
}