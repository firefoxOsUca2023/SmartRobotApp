package com.example.zacatales.smartrobotapp

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothManager
import android.bluetooth.BluetoothSocket
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.view.WindowInsets
import android.view.WindowInsetsController
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
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
    private val hideNavigationBarDelayMillis: Long = 2500
    private val handler = Handler()
    private val hideNavigationBarRunnable = Runnable { hideNavigationBar() }

    companion object{
        var myUUID: UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB")
        var bluetoothSocket: BluetoothSocket?=null
        var isConnected: Boolean = false

    }

    override fun onResume() {
        super.onResume()
        bluetoothManager = com.example.zacatales.smartrobotapp.Bluetooth.`interface`.BluetoothManager(this,this)
        bluetoothManager.setBluetoothStateListener(this)
        hideNavigationBar()
    }

    override fun onPause() {
        super.onPause()
        bluetoothManager = com.example.zacatales.smartrobotapp.Bluetooth.`interface`.BluetoothManager(this,this)
        bluetoothManager.setBluetoothStateListener(this)

        handler.removeCallbacks(hideNavigationBarRunnable)

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

        hideNavigationBar()

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
        //Log.i("Exito","COnectado")
    }
    override fun enviarComandoBluetooth(comando: String) {
        bluetoothManager.enviarComando(comando)
        Log.i("Comannd","Enviado")
    }
    override fun onBluetoothConnectionError(error: String) {
        Log.i("Error","Falló la conexión")
        runOnUiThread {
            Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
        }
        //Toast.makeText(this,error,Toast.LENGTH_LONG).show()
    }

    override fun onBluetoothDisconnected() {
        //showToast("Conexión Bluetooth cerrada")
    }

    override fun State(state: Boolean) {
        if(state){
            runOnUiThread {
                Toast.makeText(this, "1", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun hideNavigationBar() {
        window.decorView.apply {
            systemUiVisibility = (
                    View.SYSTEM_UI_FLAG_IMMERSIVE
                            or View.SYSTEM_UI_FLAG_FULLSCREEN
                            or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    )
        }

        handler.removeCallbacks(hideNavigationBarRunnable)
        handler.postDelayed(hideNavigationBarRunnable, hideNavigationBarDelayMillis)
    }
}