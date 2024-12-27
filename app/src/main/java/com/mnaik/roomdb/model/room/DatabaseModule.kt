package com.mnaik.roomdb.model.room

import android.content.Context
import androidx.room.Room
import com.mnaik.roomdb.utils.Constant
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by Monil Naik on 27-12-2024.
 */
@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): Database {
        return Room.databaseBuilder(
            context.applicationContext,
            Database::class.java,
            Constant.DATABASE
        ).build()
    }

    @Provides
    @Singleton
    fun provideUserDao(appDatabase: Database): DeviceDao {
        return appDatabase.deviceDao()
    }
}