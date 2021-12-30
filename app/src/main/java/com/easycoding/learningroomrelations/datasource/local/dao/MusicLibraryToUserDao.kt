package com.easycoding.learningroomrelations.datasource.local.dao

import androidx.room.Dao
import com.easycoding.learningroomrelations.datasource.local.entities.UserMusicLibraryCrossRef

@Dao
interface MusicLibraryToUserDao: BaseDao<UserMusicLibraryCrossRef>