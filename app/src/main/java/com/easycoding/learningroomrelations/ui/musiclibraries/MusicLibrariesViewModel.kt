package com.easycoding.learningroomrelations.ui.musiclibraries

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.easycoding.learningroomrelations.database.dao.UserDao
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MusicLibrariesViewModel @Inject constructor(
    userDao: UserDao,
    savedStateHandle: SavedStateHandle
): ViewModel() {
    val userMusicLibraries = userDao.getUserMusicLibraries(savedStateHandle.get<Int>("userId") ?: 1).asLiveData()
}