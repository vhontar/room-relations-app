package com.easycoding.learningroomrelations.datasource.local.entities

import androidx.room.Entity

@Entity(primaryKeys = ["songId", "musicLibraryId"])
data class SongMusicLibraryCrossRef(
    val songId: Int,
    val musicLibraryId: Int
)