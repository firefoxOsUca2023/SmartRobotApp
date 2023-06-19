package com.example.zacatales.smartrobotapp.Bluetooth.viewmodel


import android.annotation.SuppressLint
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothSocket
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.zacatales.smartrobotapp.Bluetooth.model.PairedDevicesInfo
import com.example.zacatales.smartrobotapp.DeviceReviewerApplication
import com.example.zacatales.smartrobotapp.Bluetooth.repositories.DeviceRepository
import com.example.zacatales.smartrobotapp.BluetoothFragment.Companion.isConnected
import com.example.zacatales.smartrobotapp.MainActivity
import java.io.IOException
import java.sql.Connection
import java.util.UUID


class DeviceViewModel(private val repository: DeviceRepository): ViewModel() {
    var name = MutableLiveData("")
    var macAddress = MutableLiveData("")
    var status = MutableLiveData("")
    var connect = MutableLiveData("")


    fun getDevices() = repository.getDevices()

    fun setSelectedDevice(device: PairedDevicesInfo) {
        name.value = device.name
        macAddress.value = device.macAddress
    }


    companion object {
        val Factory = viewModelFactory {
            initializer {
                val app = this[
                        ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY
                ] as DeviceReviewerApplication
                DeviceViewModel(app.deviceRepository)
            }
        }
        const val INACTIVE = ""

    }
}