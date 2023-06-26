package com.example.zacatales.smartrobotapp.Bluetooth.`interface`




import android.annotation.SuppressLint
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothSocket
import android.content.Context
import android.widget.Toast
import com.example.zacatales.smartrobotapp.BluetoothFragment
import com.example.zacatales.smartrobotapp.R
import java.io.IOException
import java.io.OutputStream
import java.util.*
class BluetoothManager(private val context: Context, private var listener: BluetoothConnectionListener) {

    fun setBluetoothStateListener(listener: BluetoothConnectionListener) {
        this@BluetoothManager.listener = listener
    }

    fun isConnected(): Boolean {
        // Devuelve el estado de conexión basado en la disponibilidad del socket Bluetooth
        return bluetoothSocket != null && bluetoothSocket!!.isConnected
    }
    fun getBluetoothSocket(): BluetoothSocket? {
        return bluetoothSocket
    }


    @SuppressLint("MissingPermission")
    fun conectarDispositivo(address: String) {
        val device: BluetoothDevice? = bluetoothAdapter?.getRemoteDevice(address)
        var state: Boolean
        state = true

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
                listener.State(state)
                outputStream = bluetoothSocket?.outputStream
                if (outputStream != null) {
                    // La conexión ha sido establecida, realiza operaciones de lectura/escritura aquí
                    listener.onBluetoothConnected(address)
                    listener.State(state)
                    //connectionListener?.Success()
                } else {
                    // No se pudo obtener el outputStream
                    //val error = "Error al mandar el comando"
                    //showToast(error)
                    //listener.onBluetoothConnectionError(error)
                    //listener.onBluetoothConnectionError("Error no se pudo obtener outputStream")
                    //listener.onBluetoothConnectionError("Error: no se pudo recibir conectar")
                }

            } catch (e: IOException) {
                // Ocurrió un error al establecer la conexión
                //listener.onBluetoothConnectionError("Error no se pudo conectar")
                //state=false
                //listener.State(state)
                val error = "Error: no se pudo conectar al dispositivo"
                listener.onBluetoothConnectionError(error)
                //listener.onBluetoothConnectionError(error)
            }
        }.start()
    }

    fun enviarComando(comando: String) {
        if(bluetoothSocket==null){
            val error = "Conecte su dispositivo a bluetooth"
            listener.onBluetoothConnectionError(error)
        }else{
            if (outputStream != null) {
                try {
                    outputStream?.write(comando.toByteArray())
                    outputStream?.flush()
                } catch (e: IOException) {
                    val error = "Todavia no se ha conectado a ningún dispostivo"
                    listener.onBluetoothConnectionError(error)
                    //listener.onBluetoothConnectionError(error)
                }
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
            val error = "Error al desconectar"
            listener.onBluetoothConnectionError(error)
            //showToast(error)
        }
    }
    companion object {
        private val MY_UUID: UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB")
        private val bluetoothAdapter: BluetoothAdapter? = BluetoothAdapter.getDefaultAdapter()
        private var bluetoothSocket: BluetoothSocket? = null
        private var outputStream: OutputStream? = null
    }
}