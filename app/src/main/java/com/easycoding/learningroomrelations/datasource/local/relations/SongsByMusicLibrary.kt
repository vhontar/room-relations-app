package com.easycoding.learningroomrelations.datasource.local.relations

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.easycoding.learningroomrelations.business.models.MusicLibrary
import com.easycoding.learningroomrelations.business.models.Song
import com.easycoding.learningroomrelations.datasource.local.entities.SongMusicLibraryCrossRef

data class SongsByMusicLibrary(
    @Embedded val musicLibrary: MusicLibrary,
    @Relation(
        parentColumn = "musicLibraryId",
        entityColumn = "songId",
        entity = Song::class,
        associateBy = Junction(SongMusicLibraryCrossRef::class)
    )
    val songs: List<Song>
)