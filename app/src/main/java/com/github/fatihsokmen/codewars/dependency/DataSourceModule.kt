package com.github.fatihsokmen.codewars.dependency

import android.app.Application
import android.arch.persistence.room.Room
import com.github.fatihsokmen.codewars.data.local.SearchDatabase
import com.github.fatihsokmen.codewars.data.local.UserDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
abstract class DataSourceModule {

    @Module
    companion object {

        @JvmStatic
        @Provides
        @Singleton
        fun provideSearchDatabase(app: Application): SearchDatabase =
                Room.databaseBuilder(
                        app.applicationContext, SearchDatabase::class.java, "search_db"
                ).fallbackToDestructiveMigration().build()

        @JvmStatic
        @Provides
        @Singleton
        fun provideUserDao(database: SearchDatabase): UserDao =
                database.userDao()
    }
}
