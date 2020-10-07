package com.progtech.pikabufeed

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class FeedFragment : Fragment() {

    var notConnected: TextView? = null
    var recyclerView: RecyclerView? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_feed_list, container, false)

        val retrofit = Retrofit.Builder()
                .baseUrl("https://pikabu.ru/page/interview/mobile-app/test-api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        val feedApi: FeedApi = retrofit.create<FeedApi>(FeedApi::class.java)

        val feeds: Call<List<FeedItem>> = feedApi.feeds()

        recyclerView = view.findViewById(R.id.recyclerFeed)
        notConnected = view.findViewById(R.id.textNotConnected)

        feeds.enqueue(object : Callback<List<FeedItem>?> {
            override fun onResponse(call: Call<List<FeedItem>?>, response: Response<List<FeedItem>?>) {
                Log.d("Call", "response " + response.body()?.size.toString())

                //response.body()?.forEach {
                //    it.images?.forEach { Log.d("Item", it) }
                //}

                recyclerView?.run {
                    val respBody = response.body()
                    respBody?.let {
                        adapter = FeedItemRecyclerViewAdapter(context, it)
                    }
                }
            }

            override fun onFailure(call: Call<List<FeedItem>?>, t: Throwable) {
                notConnected?.visibility = View.VISIBLE
                recyclerView?.visibility = View.INVISIBLE
            }
        })

        return view
    }
}
