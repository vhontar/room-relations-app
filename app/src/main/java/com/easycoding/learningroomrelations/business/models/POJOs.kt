package com.easycoding.learningroomrelations.pojos

import com.easycoding.learningroomrelations.business.models.*
import com.easycoding.learningroomrelations.datasource.local.entities.SongMusicLibraryCrossRef
import com.easycoding.learningroomrelations.datasource.local.entities.UserMusicLibraryCrossRef

class Response(
    val users: List<UserPojo>,
    val music_libraries: List<MusicLibraryPojo>,
    val music_libraries_to_users: List<MusicLibraryToUserPojo>,
    val songs: List<SongPojo>,
    val songs_to_music_libraries: List<SongToMusicLibraryPojo>
)

class UserPojo(
    val id: Int,
    val firstname: String,
    val lastname: String,
    val email: String
)

fun UserPojo.toModel(): User {
    return User(id, firstname, lastname, email)
}

class MusicLibraryPojo(
    val id: Int,
    val name: String,
    val description: String
)

fun MusicLibraryPojo.toModel(): MusicLibrary {
    return MusicLibrary(id, name, description)
}

class MusicLibraryToUserPojo(
    val user_id: Int,
    val music_library_id: Int
)

fun MusicLibraryToUserPojo.toModel(): UserMusicLibraryCrossRef {
    return UserMusicLibraryCrossRef(user_id, music_library_id)
}

class SongPojo(
    val id: Int,
    val name: String,
    val singer_first_name: String,
    val singer_last_name: String,
    val description: String
)

fun SongPojo.toModel(): Song {
    return Song(id, name, singer_first_name, singer_last_name, description)
}

class SongToMusicLibraryPojo(
    val song_id: Int,
    val music_library_id: Int
)

fun SongToMusicLibraryPojo.toModel(): SongMusicLibraryCrossRef {
    return SongMusicLibraryCrossRef(song_id, music_library_id)
}