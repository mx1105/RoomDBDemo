package com.mnaik.roomdb.model.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.mnaik.roomdb.model.Device
import com.mnaik.roomdb.model.room.DeviceDao
import com.mnaik.roomdb.model.room.DeviceEntity
import com.mnaik.roomdb.model.toDevice
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Monil Naik on 27-12-2024.
 */
@Singleton
class DeviceRepository @Inject constructor(private val deviceDao: DeviceDao) {

    val allDevice: LiveData<List<Device>> = deviceDao.getAllDevices().map { deviceEntities ->
        deviceEntities.map { it.toDevice() }
    }

    suspend fun addDevice(deviceEntity: DeviceEntity) {
        deviceDao.addDevice(deviceEntity)
    }

    suspend fun removeDevice(deviceEntity: DeviceEntity) {
        deviceDao.removeDevice(deviceEntity)
    }
}