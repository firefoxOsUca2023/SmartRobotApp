package com.example.zacatales.smartrobotapp

import android.app.Instrumentation
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothManager
import android.content.Intent
import android.content.res.Configuration
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.example.zacatales.smartrobotapp.databinding.ActivityMainBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var btPermissions = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= DataBindingUtil.
        setContentView(this,R.layout.activity_main)
        val bluetoothManager: BluetoothManager = getSystemService(BluetoothManager::class.java)
        val bluetoothAdapter:BluetoothAdapter = bluetoothManager.adapter
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
    }
    private val bluetoothPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ){ isGranted:Boolean ->
        if(isGranted){
            val bluetoothManager:BluetoothManager = getSystemService(BluetoothManager::class.java)
            val bluetoothAdapter:BluetoothAdapter?=bluetoothManager.adapter
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
        Toast.makeText(this,"Bluetooth conectado con exito",Toast.LENGTH_LONG).show()
    }


}