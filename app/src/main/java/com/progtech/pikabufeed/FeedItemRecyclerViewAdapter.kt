package com.progtech.pikabufeed

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager


class FeedItemRecyclerViewAdapter(
        private val ctx: Context,
        private val values: List<FeedItem>)
    : RecyclerView.Adapter<FeedItemRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.fragment_feed_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.titleView.text = item.title
        holder.bodyView.text = item.body

        if (item.images != null && item.images.isNotEmpty()) {
            val adapter = ViewPagerAdapter(ctx, item.images)
            holder.viewPager.adapter = adapter
        } else holder.viewPager.visibility = View.GONE

    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val titleView: TextView = view.findViewById(R.id.title)
        val bodyView: TextView = view.findViewById(R.id.body)
        val viewPager: ViewPager = view.findViewById(R.id.view_pager)

        override fun toString(): String {
            return super.toString() + " '" + bodyView.text + "'"
        }
    }
}
