package com.progtech.pikabufeed

import retrofit2.Call
import retrofit2.http.GET


interface FeedApi {
    @GET("feed.php")
    fun feeds(): Call<List<FeedItem>>
}