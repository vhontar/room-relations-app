package com.easycoding.learningroomrelations.database

import android.content.Context
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.easycoding.learningroomrelations.database.dao.*
import com.easycoding.learningroomrelations.di.ApplicationScope
import com.easycoding.learningroomrelations.models.*
import com.easycoding.learningroomrelations.pojos.Response
import com.easycoding.learningroomrelations.pojos.toModel
import com.easycoding.learningroomrelations.utils.AssetsUtils
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Provider

const val APP_DATABASE_NAME = "app.db"

@Database(
    entities = [
        User::class,
        MusicLibrary::class,
        Song::class,
        UserMusicLibraryCrossRef::class,
        SongMusicLibraryCrossRef::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase: RoomDatabase() {
    abstract fun getUserDao(): UserDao
    abstract fun getMusicLibraryDao(): MusicLibraryDao
    abstract fun getSongDao(): SongDao
    abstract fun getMusicLibraryToUserDao(): MusicLibraryToUserDao
    abstract fun getSongToMusicLibraryDao(): SongToMusicLibraryDao

    class Callback @Inject constructor(
        // Provider helps to inject the database after hilt creates it.
        private val database: Provider<AppDatabase>,
        @ApplicationContext private val context: Context,
        @ApplicationScope private val applicationScope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)

            val result = AssetsUtils.parseJSON<Response>(context.assets)
            applicationScope.launch {
                val appDatabase = database.get()
                result.users.forEach {
                    appDatabase.getUserDao().insert(it.toModel())
                }
                result.music_libraries.forEach {
                    appDatabase.getMusicLibraryDao().insert(it.toModel())
                }
                result.songs.forEach {
                    appDatabase.getSongDao().insert(it.toModel())
                }
                result.music_libraries_to_users.forEach {
                    appDatabase.getMusicLibraryToUserDao().insert(it.toModel())
                }
                result.songs_to_music_libraries.forEach {
                    appDatabase.getSongToMusicLibraryDao().insert(it.toModel())
                }
            }
        }
    }
}