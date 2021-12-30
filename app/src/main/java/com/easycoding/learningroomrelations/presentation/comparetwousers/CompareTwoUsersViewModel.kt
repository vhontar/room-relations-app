package com.easycoding.learningroomrelations.presentation.comparetwousers

import androidx.lifecycle.*
import com.easycoding.learningroomrelations.datasource.local.dao.MusicLibraryDao
import com.easycoding.learningroomrelations.datasource.local.dao.UserDao
import com.easycoding.learningroomrelations.business.models.MusicLibrary
import com.easycoding.learningroomrelations.business.models.Song
import com.easycoding.learningroomrelations.business.models.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CompareTwoUsersViewModel @Inject constructor(
    private val userDao: UserDao,
    private val musicLibraryDao: MusicLibraryDao
): ViewModel() {
    val users = userDao.getAllUsers().asLiveData()

    private val _similarMusicLibraries = MutableLiveData<List<MusicLibrary>>()
    val similarMusicLibraries: LiveData<List<MusicLibrary>> = _similarMusicLibraries

    private val _similarSongs = MutableLiveData<List<Song>>()
    val similarSongs: LiveData<List<Song>> = _similarSongs

    private var firstSelectedUser: User? = null
    private var secondSelectedUser: User? = null

    fun selectFirstUser(user: User?) {
        firstSelectedUser = user
    }

    fun selectSecondUser(user: User?) {
        secondSelectedUser = user
    }

    fun onFindClicked() {
        if (firstSelectedUser == null || secondSelectedUser == null)
            return

        loadSimilarPlaylistsAndSongs()
    }

    private fun loadSimilarPlaylistsAndSongs() = viewModelScope.launch {
        val similarMusicLibraries = userDao.getSimilarUsersMusicLibraries(
            firstSelectedUser!!.userId,
            secondSelectedUser!!.userId
        )

        val allPlaylists = similarMusicLibraries?.flatMap { it.musicLibraries } ?: emptyList()
        _similarMusicLibraries.value = allPlaylists
            .groupBy { it.musicLibraryId }
            .filter { it.value.size > 1 }
            .flatMap { it.value }
            .distinct()
            .sortedBy { it.musicLibraryId }

        val musicLibrariesIds = allPlaylists.map { it.musicLibraryId }
        val allSongs = musicLibraryDao.getMusicLibrariesSongs(musicLibrariesIds)?.flatMap { it.songs } ?: emptyList()
        _similarSongs.value = allSongs
            .groupBy { it.songId }
            .filter { it.value.size > 1 }
            .flatMap { it.value }
            .distinct()
            .sortedBy { it.songId }
    }
}