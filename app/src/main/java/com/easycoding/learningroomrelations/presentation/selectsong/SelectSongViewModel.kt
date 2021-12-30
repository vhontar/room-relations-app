package com.easycoding.learningroomrelations.presentation.selectsong

import androidx.lifecycle.*
import com.easycoding.learningroomrelations.datasource.local.dao.MusicLibraryDao
import com.easycoding.learningroomrelations.datasource.local.dao.SongDao
import com.easycoding.learningroomrelations.business.models.MusicLibrary
import com.easycoding.learningroomrelations.business.models.Song
import com.easycoding.learningroomrelations.business.models.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
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

    private var selectedSong: Song? = null

    fun selectSong(song: Song?) {
        selectedSong = song
    }

    fun onFindClicked() {
        if (selectedSong == null)
            return

        loadSimilarMusicLibrariesAndUsers()
    }

    private fun loadSimilarMusicLibrariesAndUsers() = viewModelScope.launch {
        val foundMusicLibraries = songDao.getSongMusicLibraries(selectedSong!!.songId)
            .map { it.musicLibraries }
            .asLiveData()
            .value ?: emptyList()
        _musicLibraries.value = foundMusicLibraries

        val musicLibrariesIds = foundMusicLibraries.map { it.musicLibraryId }
        _users.value = musicLibraryDao.getMusicLibrariesUsers(musicLibrariesIds)
            .flatMap { it.users }
            .distinct()
            .sortedBy { it.userId }
    }
}