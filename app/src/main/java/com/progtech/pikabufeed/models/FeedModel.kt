package com.progtech.pikabufeed.models

import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class FeedModel() : IModel {
    private val feedApi = Retrofit.Builder()
            .baseUrl("https://pikabu.ru/page/interview/mobile-app/test-api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create<FeedApi>(FeedApi::class.java)

    override fun getItems(listener: (List<Post>?) -> Unit) {
        val feeds: Call<List<FeedItem>> = feedApi.feeds()

        feeds.enqueue(object : Callback<List<FeedItem>?> {
            override fun onResponse(call: Call<List<FeedItem>?>, response: Response<List<FeedItem>?>) {
                Log.d("Call", "response " + response.body()?.size.toString())
                val respBody = response.body()
                respBody?.run {
                    val list = map { Post(it) }
                    listener.invoke(list)
                } ?: listener.invoke(null)
            }

            override fun onFailure(call: Call<List<FeedItem>?>, t: Throwable) {
                listener.invoke(null)
            }
        })
    }

    override fun getItem(id: Int, listener: (post: Post?) -> Unit) {
        val feed = feedApi.detail(id)

        feed.enqueue(object: Callback<FeedItem> {
            override fun onResponse(call: Call<FeedItem>, response: Response<FeedItem>) {
                val respBody = response.body()
                respBody?.let { listener.invoke(Post(it)) } ?: listener(null)
            }

            override fun onFailure(call: Call<FeedItem>, t: Throwable) {
                listener(null)
            }

        })
    }

}