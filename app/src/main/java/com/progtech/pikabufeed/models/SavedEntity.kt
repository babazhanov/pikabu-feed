package com.progtech.pikabufeed.models

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
class SavedEntity {
    @PrimaryKey
    var id: Int = 0
    var title: String = ""
    var body: String = ""
}