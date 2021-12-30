package com.easycoding.learningroomrelations.datasource.local.dao

import androidx.room.*
import com.easycoding.learningroomrelations.business.models.MusicLibrary
import com.easycoding.learningroomrelations.datasource.local.relations.SongsByMusicLibrary
import com.easycoding.learningroomrelations.datasource.local.relations.UsersByMusicLibrary
import kotlinx.coroutines.flow.Flow

@Dao
interface MusicLibraryDao: BaseDao<MusicLibrary> {
    @Transaction
    @Query("SELECT * FROM MusicLibrary WHERE musicLibraryId = :musicLibraryId")
    fun getMusicLibrarySongs(musicLibraryId: Int): Flow<SongsByMusicLibrary>

    @Transaction
    @Query("SELECT * FROM MusicLibrary WHERE musicLibraryId IN (:ids)")
    suspend fun getMusicLibrariesSongs(ids: List<Int>): List<SongsByMusicLibrary>?

    @Transaction
    @Query("SELECT * FROM MusicLibrary WHERE musicLibraryId = :musicLibraryId")
    fun getMusicLibraryUsers(musicLibraryId: Int): Flow<UsersByMusicLibrary>

    @Transaction
    @Query("SELECT * FROM MusicLibrary WHERE musicLibraryId IN (:ids)")
    suspend fun getMusicLibrariesUsers(ids: List<Int>): List<UsersByMusicLibrary>
}