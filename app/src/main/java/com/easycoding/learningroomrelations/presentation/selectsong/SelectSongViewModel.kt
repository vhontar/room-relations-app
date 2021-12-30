package com.easycoding.learningroomrelations.presentation.selectsong

import androidx.lifecycle.*
import com.easycoding.learningroomrelations.business.models.MusicLibrary
import com.easycoding.learningroomrelations.business.models.Song
import com.easycoding.learningroomrelations.business.models.User
import com.easycoding.learningroomrelations.datasource.local.dao.MusicLibraryDao
import com.easycoding.learningroomrelations.datasource.local.dao.SongDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SelectSongViewModel @Inject constructor(
    private val songDao: SongDao,
    private val musicLibraryDao: MusicLibraryDao
) : ViewModel() {

    val songs = songDao.getAllSongs().asLiveData()

    private val _musicLibraries = MutableLiveData<List<MusicLibrary>>()
    val musicLibraries: LiveData<List<MusicLibrary>> = _musicLibraries

    private val _users = MutableLiveData<List<User>>()
    val users: LiveData<List<User>> = _users

    fun loadMusicLibrariesAndUsersBySong(song: Song?) = viewModelScope.launch {
        if (song == null)
            return@launch

        val musicLibrariesAndSongs = songDao.getMusicLibrariesAndUsersBySong(song.songId) ?: return@launch

        _musicLibraries.value = musicLibrariesAndSongs.usersByMusicLibrary
            .map { it.musicLibrary }
            .sortedBy { it.musicLibraryId }

        _users.value = musicLibrariesAndSongs.usersByMusicLibrary
            .flatMap { it.users }
            .distinct()
            .sortedBy { it.userId }
    }
}