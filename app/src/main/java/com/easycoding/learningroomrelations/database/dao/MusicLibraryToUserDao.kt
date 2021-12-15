package com.easycoding.learningroomrelations.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.easycoding.learningroomrelations.models.UserMusicLibraryCrossRef

@Dao
interface MusicLibraryToUserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(musicLibraryToUser: UserMusicLibraryCrossRef): Long
}