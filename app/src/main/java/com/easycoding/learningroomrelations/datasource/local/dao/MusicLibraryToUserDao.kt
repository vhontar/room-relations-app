package com.easycoding.learningroomrelations.datasource.local.dao

import androidx.room.Dao
import com.easycoding.learningroomrelations.business.models.UserMusicLibraryCrossRef

@Dao
interface MusicLibraryToUserDao: BaseDao<UserMusicLibraryCrossRef>