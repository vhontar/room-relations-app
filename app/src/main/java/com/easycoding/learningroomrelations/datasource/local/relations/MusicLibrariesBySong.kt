package com.easycoding.learningroomrelations.datasource.local.relations

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.easycoding.learningroomrelations.business.models.MusicLibrary
import com.easycoding.learningroomrelations.business.models.Song
import com.easycoding.learningroomrelations.datasource.local.entities.SongMusicLibraryCrossRef

data class MusicLibrariesBySong(
    @Embedded val song: Song,
    @Relation(
        parentColumn = "songId",
        entityColumn = "musicLibraryId",
        entity = MusicLibrary::class,
        associateBy = Junction(SongMusicLibraryCrossRef::class)
    )
    val musicLibraries: List<MusicLibrary>
)