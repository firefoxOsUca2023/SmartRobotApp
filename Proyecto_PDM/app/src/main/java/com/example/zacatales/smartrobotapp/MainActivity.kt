package com.example.zacatales.smartrobotapp

import android.annotation.SuppressLint
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothManager
import android.bluetooth.BluetoothSocket
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.example.zacatales.smartrobotapp.Bluetooth.`interface`.BluetoothConnectionListener
import com.example.zacatales.smartrobotapp.Bluetooth.model.PairedDevicesInfo
import com.example.zacatales.smartrobotapp.Bluetooth.recyclerview.PairedListAdapter
import com.example.zacatales.smartrobotapp.Bluetooth.viewmodel.DeviceViewModel
import com.example.zacatales.smartrobotapp.databinding.ActivityMainBinding
import java.io.IOException
import java.util.UUID

class MainActivity : AppCompatActivity(),BluetoothConnectionListener {
    private lateinit var binding: ActivityMainBinding
    private var btPermissions = false
    lateinit var address: String
    lateinit var mbluetoothAdapter: BluetoothAdapter
    private lateinit var bluetoothManager: com.example.zacatales.smartrobotapp.Bluetooth.`interface`.BluetoothManager
    //lateinit var bluetoothManager: com.example.zacatales.smartrobotapp.Bluetooth.`interface`.BluetoothManager

    companion object{
        var myUUID: UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB")
        var bluetoothSocket: BluetoothSocket?=null
        var isConnected: Boolean = false
    }

    @SuppressLint("MissingPermission")
    /*override fun onDatosEnviados(datos: String) {
        // Hacer algo con los datos recibidos en la actividad
        Toast.makeText(this,datos,Toast.LENGTH_LONG).show()
        try {
            if(bluetoothSocket == null || !isConnected){
                mbluetoothAdapter = BluetoothAdapter.getDefaultAdapter()
                val Device: BluetoothDevice = mbluetoothAdapter.getRemoteDevice(datos)
                bluetoothSocket = Device.createRfcommSocketToServiceRecord(myUUID)
                BluetoothAdapter.getDefaultAdapter().cancelDiscovery()
                bluetoothSocket!!.connect()
            }
            Toast.makeText(this,"Conexión exitosa",Toast.LENGTH_LONG).show()

        }catch (e: IOException){
            e.printStackTrace()
            Toast.makeText(this,"Error de conexión",Toast.LENGTH_LONG).show()
        }

    }*/


    /*override fun onDatosRecibidos(dato: String) {
        // Aquí puedes hacer lo que desees con el dato recibido
        Toast.makeText(this, "Dato recibido: $dato", Toast.LENGTH_SHORT).show()
        /*try {
            if(bluetoothSocket == null || !isConnected){
                mbluetoothAdapter = BluetoothAdapter.getDefaultAdapter()
                val Device: BluetoothDevice = mbluetoothAdapter.getRemoteDevice(dato)
                bluetoothSocket = Device.createRfcommSocketToServiceRecord(myUUID)
                BluetoothAdapter.getDefaultAdapter().cancelDiscovery()
                bluetoothSocket!!.connect()
            }
            Toast.makeText(this,"Conexión exitosa",Toast.LENGTH_LONG).show()

        }catch (e: IOException){
            e.printStackTrace()
            Toast.makeText(this,"Error de conexión",Toast.LENGTH_LONG).show()
        }*/
    }*/


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= DataBindingUtil.
        setContentView(this,R.layout.activity_main)
        bluetoothManager = com.example.zacatales.smartrobotapp.Bluetooth.`interface`.BluetoothManager(this,this)
        //val mac = "98:D3:71:F5:B3:2A"

        //val segundoFragmento = ControllersFragment.newInstance(bluetoothManager)


        val bluetoothManager: BluetoothManager = getSystemService(BluetoothManager::class.java)
        val bluetoothAdapter: BluetoothAdapter = bluetoothManager.adapter
        if(bluetoothAdapter==null){
            Toast.makeText(this,getString(R.string.notSupportBt),Toast.LENGTH_LONG).show()
        }
        else{
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.S){
                bluetoothPermissionLauncher.launch(android.Manifest.permission.BLUETOOTH_CONNECT)

            }else{
                bluetoothPermissionLauncher.launch(android.Manifest.permission.BLUETOOTH_ADMIN)
            }
        }




    }


    private val bluetoothPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ){ isGranted:Boolean ->
        if(isGranted){
            val bluetoothManager: BluetoothManager = getSystemService(BluetoothManager::class.java)
            val bluetoothAdapter: BluetoothAdapter?=bluetoothManager.adapter
            btPermissions = true
            if(bluetoothAdapter?.isEnabled==false){
                val enableBtIntent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
                btActivityResultLauncher.launch(enableBtIntent)
            }else {
                can()

            }
        }else {
            btPermissions=false
        }
    }
    private val btActivityResultLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ){
            result : ActivityResult ->
        if (result.resultCode == RESULT_OK){
            can()
        }
    }

    fun can(){
        Toast.makeText(this,getString(R.string.connectSucessful),Toast.LENGTH_LONG).show()
    }

    override fun onBluetoothConnected(address: String) {
        bluetoothManager.conectarDispositivo(address)
    }


    override fun enviarComandoBluetooth(comando: String) {
        bluetoothManager.enviarComando(comando)

    }

    override fun onBluetoothConnectionError(error: String) {
        //Toast.makeText(this,error,Toast.LENGTH_SHORT).show()
    }

    override fun onBluetoothDisconnected() {

    }


}