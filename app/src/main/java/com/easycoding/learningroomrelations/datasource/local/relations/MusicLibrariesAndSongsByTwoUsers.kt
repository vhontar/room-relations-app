package com.easycoding.learningroomrelations.datasource.local.relations

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.easycoding.learningroomrelations.business.models.MusicLibrary
import com.easycoding.learningroomrelations.business.models.User
import com.easycoding.learningroomrelations.datasource.local.entities.UserMusicLibraryCrossRef

data class MusicLibrariesAndSongsByTwoUsers(
    @Embedded val user: User,
    @Relation(
        parentColumn = "userId",
        entityColumn = "musicLibraryId",
        entity = MusicLibrary::class, // super important
        associateBy = Junction(UserMusicLibraryCrossRef::class)
    )
    val songsByMusicLibraries: List<SongsByMusicLibrary>
)
