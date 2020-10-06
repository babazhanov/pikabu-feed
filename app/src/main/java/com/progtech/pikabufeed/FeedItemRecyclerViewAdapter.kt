package com.progtech.pikabufeed

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso


class FeedItemRecyclerViewAdapter(
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

        item.images?.forEach { Picasso.get().load(it).into(holder.imageView); }
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val titleView: TextView = view.findViewById(R.id.title)
        val bodyView: TextView = view.findViewById(R.id.body)
        val imageView: ImageView = view.findViewById(R.id.imageView)

        override fun toString(): String {
            return super.toString() + " '" + bodyView.text + "'"
        }
    }
}
