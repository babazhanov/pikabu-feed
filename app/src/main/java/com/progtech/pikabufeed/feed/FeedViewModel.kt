package com.progtech.pikabufeed.feed

import androidx.lifecycle.ViewModel
import com.progtech.pikabufeed.models.FeedModel
import com.progtech.pikabufeed.models.Post

class FeedViewModel : ViewModel() {
    private val feedModel: FeedModel = FeedModel()

    fun getItems(callback: (List<Post>?) -> Unit) {
        feedModel.getItems (callback)
    }

}