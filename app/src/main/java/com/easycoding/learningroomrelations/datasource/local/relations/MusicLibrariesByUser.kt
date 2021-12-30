package com.easycoding.learningroomrelations.datasource.local.relations

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.easycoding.learningroomrelations.business.models.MusicLibrary
import com.easycoding.learningroomrelations.business.models.User
import com.easycoding.learningroomrelations.datasource.local.entities.UserMusicLibraryCrossRef

data class MusicLibrariesByUser(
    @Embedded val user: User,
    @Relation(
        parentColumn = "userId",
        entityColumn = "musicLibraryId",
        entity = MusicLibrary::class,
        associateBy = Junction(UserMusicLibraryCrossRef::class)
    )
    val musicLibraries: List<MusicLibrary>
)