package com.easycoding.learningroomrelations.ui.comparetwousers

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.easycoding.learningroomrelations.database.dao.MusicLibraryDao
import com.easycoding.learningroomrelations.database.dao.SongDao
import com.easycoding.learningroomrelations.database.dao.UserDao
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CompareTwoUsersViewModel @Inject constructor(
    userDao: UserDao,
    musicLibraryDao: MusicLibraryDao,
    songDao: SongDao
): ViewModel() {
    val users = userDao.getAllUsers().asLiveData()
}