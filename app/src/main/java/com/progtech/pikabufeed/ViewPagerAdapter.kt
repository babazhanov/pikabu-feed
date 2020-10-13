package com.progtech.pikabufeed

import android.content.Context
import android.graphics.Bitmap
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.lifecycle.MutableLiveData
import androidx.viewpager.widget.PagerAdapter
import java.util.*


class ViewPagerAdapter internal constructor(
        private val context: Context,
        private val images: ArrayList<MutableLiveData<Bitmap?>>
)
    : PagerAdapter() {

    init {
        // BitmapFactory.decodeResource(Resources.getSystem(), android.R.drawable.list_selector_background)
        //images.forEachIndexed()
    }

    override fun getCount(): Int {
        return images.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val imageView = ImageView(context)
        imageView.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        images[position].value?.let {imageView.setImageBitmap(images[position].value)}
                ?: imageView.setImageResource(R.drawable.ic_star)
        container.addView(imageView)
        return imageView
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    override fun getItemPosition(`object`: Any): Int {
        return POSITION_NONE
    }

}