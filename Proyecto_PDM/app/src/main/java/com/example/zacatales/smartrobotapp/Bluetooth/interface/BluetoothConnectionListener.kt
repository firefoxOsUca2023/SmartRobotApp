package com.example.zacatales.smartrobotapp.Bluetooth.`interface`

import android.content.Context
import android.view.View
import android.widget.Toast

interface BluetoothConnectionListener {

    fun onBluetoothConnected(address: String)
    fun enviarComandoBluetooth(comando: String)
    fun onBluetoothConnectionError(error: String)
    fun onBluetoothDisconnected()
}
