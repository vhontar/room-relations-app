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
        entity = MusicLibrary::class, // super important if you use other relation class
        associateBy = Junction(UserMusicLibraryCrossRef::class)
    )
    val songsByMusicLibraries: List<SongsByMusicLibrary>
)
