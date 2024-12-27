package com.mnaik.roomdb.model.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.mnaik.roomdb.utils.Constant.DEVICE_TABLE

/**
 * Created by Monil Naik on 26-12-2024.
 */
@Dao
interface DeviceDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addDevice(deviceEntity: DeviceEntity)

    @Update
    fun updateDevice(deviceEntity: DeviceEntity)

    @Delete
    fun removeDevice(deviceEntity: DeviceEntity)

    @Query("SELECT * FROM $DEVICE_TABLE ORDER BY id DESC")
    fun getAllDevices(): LiveData<List<DeviceEntity>>

    @Query("SELECT * FROM $DEVICE_TABLE WHERE id LIKE :deviceId")
    fun getDevices(deviceId: Int): DeviceEntity
}