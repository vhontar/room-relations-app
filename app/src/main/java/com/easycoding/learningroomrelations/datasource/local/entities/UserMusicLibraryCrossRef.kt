package com.easycoding.learningroomrelations.datasource.local.entities

import androidx.room.Entity

@Entity(primaryKeys = ["userId", "musicLibraryId"])
data class UserMusicLibraryCrossRef(
    val userId: Int,
    val musicLibraryId: Int
)
