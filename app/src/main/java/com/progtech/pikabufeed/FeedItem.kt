package com.progtech.pikabufeed

data class FeedItem(val id: Int,
                    val title: String,
                    val body: String,
                    val images: List<String>?) {

}
