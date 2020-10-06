package com.progtech.pikabufeed

import android.net.Uri

data class FeedItem(val id: Int,
                    val title: String,
                    val body: String,
                    val imgs: List<Uri>) {

}
