package com.progtech.pikabufeed

import androidx.room.*


@Dao
interface SavedDao {
    @Query("SELECT * FROM savedentity")
    fun getAll(): List<SavedEntity>

    @Query("SELECT * FROM savedentity WHERE id = :id")
    fun getById(id: Long): SavedEntity?

    @Insert
    fun insert(employee: SavedEntity?)
}