package com.easycoding.learningroomrelations.ui.songs

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.easycoding.learningroomrelations.database.dao.MusicLibraryDao
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SongsViewModel @Inject constructor(
    musicLibraryDao: MusicLibraryDao,
    savedStateHandle: SavedStateHandle
): ViewModel() {
    val musicLibrarySongs = musicLibraryDao.getMusicLibrarySongs(savedStateHandle.get<Int>("musicLibraryId") ?: 1).asLiveData()
}