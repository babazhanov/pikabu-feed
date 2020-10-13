package com.progtech.pikabufeed.models

import androidx.room.Database
import androidx.room.RoomDatabase
import com.progtech.pikabufeed.models.SavedDao
import com.progtech.pikabufeed.models.SavedEntity


@Database(entities = [SavedEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun savedDao(): SavedDao?
}