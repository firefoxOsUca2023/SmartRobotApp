package com.example.zacatales.smartrobotapp.Bluetooth.`interface`

import android.content.Context

interface BluetoothConnectionListener {
    fun onBluetoothConnected(address: String)
    fun enviarComandoBluetooth(comando: String)
    fun onBluetoothConnectionError(error: String)
    fun onBluetoothDisconnected()
}
