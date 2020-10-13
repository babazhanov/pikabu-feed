package com.progtech.pikabufeed.models

// Интерфейс модели данных
interface IModel {
    // все посты
    fun getItems(listener: (List<Post>?) -> Unit)
    // Пост по id
    fun getItem(id: Int, listener: (post: Post?) -> Unit)
}