package com.easycoding.learningroomrelations.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.easycoding.learningroomrelations.database.APP_DATABASE_NAME
import com.easycoding.learningroomrelations.database.AppDatabase
import com.easycoding.learningroomrelations.database.dao.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context, callback: AppDatabase.Callback): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, APP_DATABASE_NAME)
            .addCallback(callback)
            .build()
    }

    @Provides
    fun provideUserDao(appDatabase: AppDatabase): UserDao {
        return appDatabase.getUserDao()
    }

    @Provides
    fun provideMusicLibraryDao(appDatabase: AppDatabase): MusicLibraryDao {
        return appDatabase.getMusicLibraryDao()
    }

    @Provides
    fun provideSongDao(appDatabase: AppDatabase): SongDao {
        return appDatabase.getSongDao()
    }

    @Provides
    fun provideMusicLibraryToUserDao(appDatabase: AppDatabase): MusicLibraryToUserDao {
        return appDatabase.getMusicLibraryToUserDao()
    }

    @Provides
    fun provideSongToMusicLibraryDao(appDatabase: AppDatabase): SongToMusicLibraryDao {
        return appDatabase.getSongToMusicLibraryDao()
    }

    @ApplicationScope
    @Singleton
    @Provides
    fun provideApplicationScope() = CoroutineScope(SupervisorJob())
}

@Qualifier
annotation class ApplicationScope