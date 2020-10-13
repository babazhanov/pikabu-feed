package com.progtech.pikabufeed.feed

import android.app.Activity
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.progtech.pikabufeed.App
import com.progtech.pikabufeed.R
import com.progtech.pikabufeed.models.SavedEntity
import com.progtech.pikabufeed.ViewPagerAdapter
import com.progtech.pikabufeed.models.Post


class FeedItemRecyclerViewAdapter(
        private val activity: FragmentActivity,
        private val values: List<Post>)
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

        if (item.images != null && item.images!!.isNotEmpty()) {
            val adapter = ViewPagerAdapter(activity, item.images!!)
            holder.viewPager.adapter = adapter

            item.images!!.forEach {
                it.observe(activity, Observer {
                    adapter.notifyDataSetChanged()
                    holder.viewPager.invalidate()
                })
            }

        }
        else holder.viewPager.visibility = View.GONE

        holder.itemView.setOnClickListener {
            Toast.makeText(activity, "${item.id}", Toast.LENGTH_SHORT).show()
        }

        holder.buttonStar.setOnClickListener {
            val saved = App.instance?.saved
            val dao = saved?.savedDao()
            val entity = SavedEntity()
            entity.id = item.id
            entity.title = item.title ?: ""
            entity.body = item.body ?: ""
            Log.d("ENTITY", entity.toString())
            dao?.insert(entity)
        }
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val titleView: TextView = view.findViewById(R.id.title)
        val bodyView: TextView = view.findViewById(R.id.body)
        val viewPager: ViewPager = view.findViewById(R.id.view_pager)
        val buttonStar: Button = view.findViewById(R.id.button_star)

        override fun toString(): String {
            return super.toString() + " '" + bodyView.text + "'"
        }
    }
}
