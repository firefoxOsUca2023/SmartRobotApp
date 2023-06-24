package com.example.zacatales.smartrobotapp

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothManager
import android.bluetooth.BluetoothSocket
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.core.content.ContentProviderCompat.requireContext
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
    private lateinit var bluetoothManager: com.example.zacatales.smartrobotapp.Bluetooth.`interface`.BluetoothManager
    //private lateinit var bluetoothManager: com.example.zacatales.smartrobotapp.Bluetooth.`interface`.BluetoothManager
    //lateinit var bluetoothManager: com.example.zacatales.smartrobotapp.Bluetooth.`interface`.BluetoothManager

    companion object{
        var myUUID: UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB")
        var bluetoothSocket: BluetoothSocket?=null
        var isConnected: Boolean = false
    }

    override fun onResume() {
        super.onResume()
        bluetoothManager = com.example.zacatales.smartrobotapp.Bluetooth.`interface`.BluetoothManager(this,this)
        bluetoothManager.setListener(this)
    }

    override fun onPause() {
        super.onPause()
        bluetoothManager = com.example.zacatales.smartrobotapp.Bluetooth.`interface`.BluetoothManager(this,this)
        bluetoothManager.setListener(this)

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= DataBindingUtil.
        setContentView(this,R.layout.activity_main)
        //val mac = "98:D3:71:F5:B3:2A"

        //val segundoFragmento = ControllersFragment.newInstance(bluetoothManager)

        val bluetoothManager: BluetoothManager = getSystemService(BluetoothManager::class.java)
        val bluetoothAdapter: BluetoothAdapter = bluetoothManager.adapter
        if(bluetoothAdapter==null){
            Toast.makeText(this,"El dispositivo no soporta Bluetooth",Toast.LENGTH_LONG).show()
        }
        else{
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.S){
                bluetoothPermissionLauncher.launch(android.Manifest.permission.BLUETOOTH_CONNECT)

            }else{
                bluetoothPermissionLauncher.launch(android.Manifest.permission.BLUETOOTH_ADMIN)
            }
        }


        window.decorView.apply {
            systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_FULLSCREEN
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
                can()
            }else {
                //can()

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
        Toast.makeText(this,"Bluetooth encendido",Toast.LENGTH_LONG).show()
    }

    override fun onBluetoothConnected(address: String) {
            bluetoothManager.conectarDispositivo(address)
        //showToast("CONECTADO CON EXITO")
        //Log.i("Exito","COnectado")
    }

    override fun enviarComandoBluetooth(comando: String) {
            bluetoothManager.enviarComando(comando)

    }

    override fun onBluetoothConnectionError(error: String) {
        //Log.i("Error","Falló la conexión")
        //Toast.makeText(this,error,Toast.LENGTH_LONG).show()
        //showToast(error)
    }

    override fun onBluetoothDisconnected() {
        //showToast("Desconectado")
    }

    private fun showToast(message: String) {
        runOnUiThread {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        }
    }



}