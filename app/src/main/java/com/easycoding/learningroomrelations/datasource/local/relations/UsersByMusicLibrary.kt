package com.easycoding.learningroomrelations.datasource.local.relations

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.easycoding.learningroomrelations.business.models.MusicLibrary
import com.easycoding.learningroomrelations.business.models.User
import com.easycoding.learningroomrelations.datasource.local.entities.UserMusicLibraryCrossRef

data class UsersByMusicLibrary(
    @Embedded val musicLibrary: MusicLibrary,
    @Relation(
        parentColumn = "musicLibraryId",
        entityColumn = "userId",
        entity = User::class,
        associateBy = Junction(UserMusicLibraryCrossRef::class)
    )
    val users: List<User>
)