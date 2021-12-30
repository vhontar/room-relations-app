package com.easycoding.learningroomrelations.presentation.songdetails

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.easycoding.learningroomrelations.datasource.local.dao.SongDao
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SongDetailsViewModel @Inject constructor(
    songDao: SongDao,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    val songMusicLibraries = songDao.getSongMusicLibraries(savedStateHandle.get<Int>("songId") ?: 1).asLiveData()
}