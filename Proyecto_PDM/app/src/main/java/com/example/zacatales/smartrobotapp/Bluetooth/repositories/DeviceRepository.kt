package com.example.zacatales.smartrobotapp.Bluetooth.repositories

import com.example.zacatales.smartrobotapp.Bluetooth.model.PairedDevicesInfo

class DeviceRepository(private val devices:
MutableList<PairedDevicesInfo>) {
    fun getDevices()= devices

    fun addDevices(device:PairedDevicesInfo)=devices.add(device)
}