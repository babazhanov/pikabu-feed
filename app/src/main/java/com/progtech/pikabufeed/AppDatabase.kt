package com.progtech.pikabufeed

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [SavedEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun savedDao(): SavedDao?
}