package com.mnaik.roomdb.model.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.mnaik.roomdb.utils.Constant
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * Created by Monil Naik on 26-12-2024.
 */
@Database(version = 1, entities = [DeviceEntity::class])
abstract class Database : RoomDatabase() {
    abstract fun deviceDao(): DeviceDao
    companion object{
        @Volatile
        private var INSTANCE: com.mnaik.roomdb.model.room.Database? = null
    }

}