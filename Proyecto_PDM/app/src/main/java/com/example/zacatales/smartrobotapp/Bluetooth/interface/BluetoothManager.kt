package com.example.zacatales.smartrobotapp.Bluetooth.`interface`




import android.annotation.SuppressLint
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothSocket
import android.content.Context
import com.example.zacatales.smartrobotapp.R
import java.io.IOException
import java.io.OutputStream
import java.util.*

class BluetoothManager(private val context: Context, private val listener: BluetoothConnectionListener) {

    private val bluetoothAdapter: BluetoothAdapter? = BluetoothAdapter.getDefaultAdapter()
    private var bluetoothSocket: BluetoothSocket? = null
    private var outputStream: OutputStream? = null

    @SuppressLint("MissingPermission")
    fun conectarDispositivo(address: String) {
        val device: BluetoothDevice? = bluetoothAdapter?.getRemoteDevice(address)

        // Verificar si el dispositivo Bluetooth es válido
        if (device == null) {
            // El dispositivo no está disponible
            return
        }

        // Establecer la conexión Bluetooth en un hilo separado
        Thread {
            try {
                bluetoothSocket = device.createRfcommSocketToServiceRecord(MY_UUID)
                bluetoothSocket?.connect()

                outputStream = bluetoothSocket?.outputStream

                if (outputStream != null) {
                    // La conexión ha sido establecida, realiza operaciones de lectura/escritura aquí
                    listener.onBluetoothConnected(address)
                } else {
                    // No se pudo obtener el outputStream
                    listener.onBluetoothConnectionError(context.getString(R.string.errrOuput))
                }

            } catch (e: IOException) {
                // Ocurrió un error al establecer la conexión
                listener.onBluetoothConnectionError(context.getString(R.string.errConecction))
            }
        }.start()
    }

    fun enviarComando(comando: String) {
        if (outputStream != null) {
            try {
                outputStream?.write(comando.toByteArray())
                outputStream?.flush()
            } catch (e: IOException) {
                listener.onBluetoothConnectionError(context.getString(R.string.errrOuput))
            }
        }
    }

    fun desconectarDispositivo() {
        try {
            outputStream?.close()
            bluetoothSocket?.close()
            listener.onBluetoothDisconnected()
        } catch (e: IOException) {
            // Ocurrió un error al desconectar el dispositivo
        }
    }

    companion object {
        private val MY_UUID: UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB")
    }
}
