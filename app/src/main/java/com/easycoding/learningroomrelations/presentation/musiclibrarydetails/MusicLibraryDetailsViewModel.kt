package com.easycoding.learningroomrelations.presentation.musiclibrarydetails

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.easycoding.learningroomrelations.datasource.local.dao.MusicLibraryDao
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MusicLibraryDetailsViewModel @Inject constructor(
    musicLibraryDao: MusicLibraryDao,
    savedStateHandle: SavedStateHandle
): ViewModel() {
    val musicLibraryUsers = musicLibraryDao.getMusicLibraryUsers(savedStateHandle.get<Int>("musicLibraryId") ?: 1).asLiveData()
}