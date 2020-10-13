package com.progtech.pikabufeed.models

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.Drawable
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target
import java.lang.Exception
import java.util.*

class Post {
    var id: Int
    var title: String?
    var body: String?
    var images: ArrayList<MutableLiveData<Bitmap?>>? = null
    var targets: ArrayList<Target>? = null

    constructor(feed: FeedItem) {
        id = feed.id
        title = feed.title
        body = feed.body

        feed.images?.let {
            images = ArrayList<MutableLiveData<Bitmap?>>()
            targets = ArrayList<Target>()
            images!!.forEach { it.value = null }

            it.forEachIndexed { index, value ->
                images!!.add(MutableLiveData<Bitmap?>(null))
                val target = object : Target {
                    override fun onBitmapLoaded(bitmap: Bitmap?, from: Picasso.LoadedFrom?) {
                        images!![index].postValue(bitmap)
                        Log.d("PICASSO", bitmap?.width.toString())
                    }

                    override fun onBitmapFailed(e: Exception?, errorDrawable: Drawable?) {
                        Log.d("PICASSO", "Failed")
                    }

                    override fun onPrepareLoad(placeHolderDrawable: Drawable?) {
                        Log.d("PICASSO", "onPrepareLoad")
                    }
                }
                targets!!.add(target)
                Picasso.get()
                        .load(value)
                        .into(target)
            }
        }
    }
}