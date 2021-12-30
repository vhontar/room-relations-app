package com.easycoding.learningroomrelations.datasource.local.dao

import androidx.room.*
import com.easycoding.learningroomrelations.business.models.Song
import com.easycoding.learningroomrelations.datasource.local.relations.MusicLibrariesAndUsersBySong
import com.easycoding.learningroomrelations.datasource.local.relations.MusicLibrariesBySong
import kotlinx.coroutines.flow.Flow

@Dao
interface SongDao: BaseDao<Song> {
    @Query("SELECT * FROM Song")
    fun getAllSongs(): Flow<List<Song>>

    @Transaction
    @Query("SELECT * FROM Song WHERE songId = :songId")
    fun getSongMusicLibraries(songId: Int): Flow<MusicLibrariesBySong>

    @Transaction
    @Query("SELECT * FROM Song WHERE songId = :songId")
    suspend fun getMusicLibrariesAndUsersBySong(songId: Int): MusicLibrariesAndUsersBySong?
}