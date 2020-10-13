package com.progtech.pikabufeed.feed

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.progtech.pikabufeed.R


class FeedFragment : Fragment() {

    var notConnected: TextView? = null
    var recyclerView: RecyclerView? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_feed_list, container, false)

        val feedViewModel = ViewModelProvider(requireActivity())[FeedViewModel::class.java]

        recyclerView = view.findViewById(R.id.recyclerFeed)

        feedViewModel.getItems {
            it?.let {
                recyclerView?.run {
                    adapter = FeedItemRecyclerViewAdapter(activity!!, it)
                }
            }
        }
/*
        val retrofit = Retrofit.Builder()
                .baseUrl("https://pikabu.ru/page/interview/mobile-app/test-api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        val feedApi: FeedApi = retrofit.create<FeedApi>(FeedApi::class.java)

        val feeds: Call<List<FeedItem>> = feedApi.feeds()


        notConnected = view.findViewById(R.id.textNotConnected)

        feeds.enqueue(object : Callback<List<FeedItem>?> {
            override fun onResponse(call: Call<List<FeedItem>?>, response: Response<List<FeedItem>?>) {
                Log.d("Call", "response " + response.body()?.size.toString())

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
*/
        return view
    }
}
