package com.mnaik.roomdb.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.mnaik.roomdb.model.Device
import androidx.lifecycle.viewModelScope
import com.mnaik.roomdb.model.repository.DeviceRepository
import com.mnaik.roomdb.model.toDeviceEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Created by Monil Naik on 27-12-2024.
 */
@HiltViewModel
class HomeViewModel @Inject constructor(private val deviceRepository: DeviceRepository) :
    ViewModel() {
    val allDevices: LiveData<List<Device>> = deviceRepository.allDevice

    fun addDevice(device: Device) {
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                deviceRepository.addDevice(device.toDeviceEntity())
            }
        }
    }

    fun removeDevice(device: Device) {
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                deviceRepository.removeDevice(device.toDeviceEntity())
            }
        }
    }

}