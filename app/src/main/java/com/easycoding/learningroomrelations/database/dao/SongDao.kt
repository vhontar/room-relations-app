package com.easycoding.learningroomrelations.database.dao

import androidx.room.*
import com.easycoding.learningroomrelations.models.Song
import com.easycoding.learningroomrelations.models.SongMusicLibraries
import kotlinx.coroutines.flow.Flow

@Dao
interface SongDao {
    @Query("SELECT * FROM Song")
    fun getAllSongs(): Flow<List<Song>>

    @Transaction
    @Query("SELECT * FROM Song WHERE songId = :songId")
    fun getSongMusicLibraries(songId: Int): Flow<SongMusicLibraries>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(song: Song): Long
}