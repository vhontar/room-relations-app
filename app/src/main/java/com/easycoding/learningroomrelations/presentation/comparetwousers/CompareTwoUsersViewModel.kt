package com.easycoding.learningroomrelations.presentation.comparetwousers

import androidx.lifecycle.*
import com.easycoding.learningroomrelations.business.models.MusicLibrary
import com.easycoding.learningroomrelations.business.models.Song
import com.easycoding.learningroomrelations.business.models.User
import com.easycoding.learningroomrelations.datasource.local.dao.UserDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CompareTwoUsersViewModel @Inject constructor(
    private val userDao: UserDao
): ViewModel() {
    val users = userDao.getAllUsers().asLiveData()

    private val _similarMusicLibraries = MutableLiveData<List<MusicLibrary>>()
    val similarMusicLibraries: LiveData<List<MusicLibrary>> = _similarMusicLibraries

    private val _similarSongsFromUsersAllMusicLibraries = MutableLiveData<List<Song>>()
    val similarSongsFromUsersAllMusicLibraries: LiveData<List<Song>> = _similarSongsFromUsersAllMusicLibraries

    private val _similarSongsFromUsersSimilarMusicLibraries = MutableLiveData<List<Song>>()
    val similarSongsFromUsersSimilarMusicLibraries: LiveData<List<Song>> = _similarSongsFromUsersSimilarMusicLibraries

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
        val musicLibrariesAndSongs = userDao.getMusicLibrariesAndSongsByTwoUsers(
            firstSelectedUser!!.userId,
            secondSelectedUser!!.userId
        )

        val allMusicLibraries = musicLibrariesAndSongs?.flatMap {
            it.songsByMusicLibraries.map { musicLibrary ->
                musicLibrary.musicLibrary.songs = musicLibrary.songs
                musicLibrary.musicLibrary
            }
        } ?: emptyList()
        val allSongs = musicLibrariesAndSongs?.flatMap {
            it.songsByMusicLibraries.flatMap { library -> library.songs }
        } ?: emptyList()

        val similarMusicLibraries = allMusicLibraries
            .groupBy { it.musicLibraryId }
            .filter { it.value.size > 1 }
            .flatMap { it.value }
            .distinct()
            .sortedBy { it.musicLibraryId }
        _similarMusicLibraries.value = similarMusicLibraries

        _similarSongsFromUsersAllMusicLibraries.value = allSongs
            .groupBy { it.songId }
            .filter { it.value.size > 1 }
            .flatMap { it.value }
            .distinct()
            .sortedBy { it.songId }

        val similarSongs = similarMusicLibraries
            .flatMap { it.songs ?: emptyList() }
            .sortedBy { it.songId }
        _similarSongsFromUsersSimilarMusicLibraries.value = similarSongs
    }
}