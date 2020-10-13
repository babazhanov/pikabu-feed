package com.progtech.pikabufeed.saved

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.progtech.pikabufeed.App
import com.progtech.pikabufeed.R
import com.progtech.pikabufeed.feed.FeedItemRecyclerViewAdapter
import com.progtech.pikabufeed.models.FeedItem


class SavedFragment : Fragment() {

    var notConnected: TextView? = null
    var recyclerView: RecyclerView? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_feed_list, container, false)

        val db = App.instance?.saved
        val savedDao = db?.savedDao()
//        val saved1 = SavedEntity()
//        saved1.id = -1
//        saved1.title = "Hello"
//        saved1.body = "asdasd"
//
//        savedDao?.insert(saved1)

        val all = savedDao?.getAll()

        recyclerView = view.findViewById(R.id.recyclerFeed)
        notConnected = view.findViewById(R.id.textNotConnected)

        /*
        recyclerView?.run {
            all?.run {
                adapter = FeedItemRecyclerViewAdapter(context, map {
                    it.run {
                        Post(id, title, body, null)
                    }
                })
            }
        }
        */

        return view
    }
}
