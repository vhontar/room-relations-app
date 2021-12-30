package com.easycoding.learningroomrelations.datasource.local.dao

import androidx.room.*
import com.easycoding.learningroomrelations.business.models.User
import com.easycoding.learningroomrelations.business.models.UserWithMusicLibraries
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao: BaseDao<User> {
    @Query("SELECT * FROM User")
    fun getAllUsers(): Flow<List<User>>

    @Transaction
    @Query("SELECT * FROM User WHERE userId = :userId")
    fun getUserMusicLibraries(userId: Int): Flow<UserWithMusicLibraries>

    @Transaction
    @Query("SELECT * FROM User WHERE userId IN (:firstUserId, :secondUserId)")
    suspend fun getSimilarUsersMusicLibraries(
        firstUserId: Int,
        secondUserId: Int
    ): List<UserWithMusicLibraries>?
}