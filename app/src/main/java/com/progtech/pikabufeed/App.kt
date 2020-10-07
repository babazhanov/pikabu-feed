package com.progtech.pikabufeed

import android.app.Application
import androidx.room.Room


class App : Application() {
    var saved: AppDatabase? = null

    override fun onCreate() {
        super.onCreate()
        instance = this
        saved = Room.databaseBuilder(this, AppDatabase::class.java, "saved")
                .allowMainThreadQueries()
                .build()
    }

    companion object {
        var instance: App? = null
    }
}