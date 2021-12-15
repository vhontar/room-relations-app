package com.easycoding.learningroomrelations.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.easycoding.learningroomrelations.models.SongMusicLibraryCrossRef
import kotlinx.coroutines.flow.Flow

@Dao
interface SongToMusicLibraryDao {
    @Query("SELECT * FROM SongMusicLibraryCrossRef")
    fun getAll(): Flow<List<SongMusicLibraryCrossRef>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(songToMusicLibrary: SongMusicLibraryCrossRef): Long
}