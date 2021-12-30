package com.easycoding.learningroomrelations.datasource.local.dao

import androidx.room.Dao
import com.easycoding.learningroomrelations.business.models.SongMusicLibraryCrossRef

@Dao
interface SongToMusicLibraryDao: BaseDao<SongMusicLibraryCrossRef>