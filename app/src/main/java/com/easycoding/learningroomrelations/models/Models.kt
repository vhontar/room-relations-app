package com.easycoding.learningroomrelations.models

import androidx.room.*

@Entity
data class User(
    @PrimaryKey val userId: Int,
    val firstname: String,
    val lastname: String,
    val email: String
)

@Entity
data class MusicLibrary(
    @PrimaryKey val musicLibraryId: Int,
    val name: String,
    val description: String
)

@Entity
data class Song(
    @PrimaryKey val songId: Int,
    val name: String,
    val signerName: String,
    val signerLastname: String,
    val description: String
)

@Entity(primaryKeys = ["userId", "musicLibraryId"])
data class UserMusicLibraryCrossRef(
    val userId: Int,
    val musicLibraryId: Int
)

@Entity(primaryKeys = ["songId", "musicLibraryId"])
data class SongMusicLibraryCrossRef(
    val songId: Int,
    val musicLibraryId: Int
)

data class UserWithMusicLibraries(
    @Embedded val user: User,
    @Relation(
        parentColumn = "userId",
        entityColumn = "musicLibraryId",
        associateBy = Junction(UserMusicLibraryCrossRef::class)
    )
    val musicLibraries: List<MusicLibrary>
)

data class SongMusicLibraries(
    @Embedded val song: Song,
    @Relation(
        parentColumn = "songId",
        entityColumn = "musicLibraryId",
        associateBy = Junction(SongMusicLibraryCrossRef::class)
    )
    val musicLibraries: List<MusicLibrary>
)

data class MusicLibrarySongs(
    @Embedded val musicLibrary: MusicLibrary,
    @Relation(
        parentColumn = "musicLibraryId",
        entityColumn = "songId",
        associateBy = Junction(SongMusicLibraryCrossRef::class)
    )
    val songs: List<Song>
)

data class MusicLibraryUsers(
    @Embedded val musicLibrary: MusicLibrary,
    @Relation(
        parentColumn = "musicLibraryId",
        entityColumn = "userId",
        associateBy = Junction(UserMusicLibraryCrossRef::class)
    )
    val users: List<User>
)