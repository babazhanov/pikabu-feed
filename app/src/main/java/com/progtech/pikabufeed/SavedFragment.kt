package com.progtech.pikabufeed

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView


class SavedFragment : Fragment() {

    var notConnected: TextView? = null
    var recyclerView: RecyclerView? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_feed_list, container, false)

        val db = App.instance?.saved
        val savedDao = db?.savedDao()
        val saved1 = SavedEntity()
        saved1.id = -1
        saved1.title = "Hello"
        saved1.body = "asdasd"

        savedDao?.insert(saved1)

        val all = savedDao?.getAll()

        recyclerView = view.findViewById(R.id.recyclerFeed)
        notConnected = view.findViewById(R.id.textNotConnected)

        recyclerView?.run {
            all?.run {
                adapter = FeedItemRecyclerViewAdapter(context, map {
                    it.run {
                        FeedItem(id, title, body, null)
                    }
                })
            }
        }

        return view
    }
}
