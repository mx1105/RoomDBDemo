package com.mnaik.roomdb.model

import com.mnaik.roomdb.model.room.DeviceEntity

/**
 * Created by Monil Naik on 27-12-2024.
 */
data class Device(val id: Int, val deviceName: String, val description: String)


fun DeviceEntity.toDevice(): Device {
    return Device(
        id = id,deviceName = title, description = description
    )
}

fun Device.toDeviceEntity(): DeviceEntity {
    return DeviceEntity(
        id = id, title = deviceName, description = description
    )
}
