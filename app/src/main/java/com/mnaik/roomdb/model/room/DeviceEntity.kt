package com.mnaik.roomdb.model.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mnaik.roomdb.utils.Constant.DEVICE_TABLE

/**
 * Created by Monil Naik on 26-12-2024.
 */
@Entity(tableName = DEVICE_TABLE)
data class DeviceEntity(
    @PrimaryKey(autoGenerate = true) val id: Int, val title: String, val description: String
)
