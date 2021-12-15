package com.easycoding.learningroomrelations.database.dao

import androidx.room.*
import com.easycoding.learningroomrelations.models.User
import com.easycoding.learningroomrelations.models.UserWithMusicLibraries
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Query("SELECT * FROM User")
    fun getAllUsers(): Flow<List<User>>

    @Transaction
    @Query("SELECT * FROM User WHERE userId = :userId")
    fun getUserMusicLibraries(userId: Int): Flow<UserWithMusicLibraries>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: User): Long
}