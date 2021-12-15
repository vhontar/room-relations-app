package com.easycoding.learningroomrelations.database.dao

import androidx.room.*
import com.easycoding.learningroomrelations.models.MusicLibrary
import com.easycoding.learningroomrelations.models.MusicLibrarySongs
import com.easycoding.learningroomrelations.models.MusicLibraryUsers
import kotlinx.coroutines.flow.Flow

@Dao
interface MusicLibraryDao {
    @Transaction
    @Query("SELECT * FROM MusicLibrary WHERE musicLibraryId = :musicLibraryId")
    fun getMusicLibrarySongs(musicLibraryId: Int): Flow<MusicLibrarySongs>

    @Transaction
    @Query("SELECT * FROM MusicLibrary WHERE musicLibraryId = :musicLibraryId")
    fun getMusicLibraryUsers(musicLibraryId: Int): Flow<MusicLibraryUsers>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(musicLibrary: MusicLibrary): Long
}