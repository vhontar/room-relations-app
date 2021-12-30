package com.easycoding.learningroomrelations.business.models

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity
data class MusicLibrary(
    @PrimaryKey val musicLibraryId: Int,
    val name: String,
    val description: String
) {
    @Ignore
    var songs: List<Song>? = null
}