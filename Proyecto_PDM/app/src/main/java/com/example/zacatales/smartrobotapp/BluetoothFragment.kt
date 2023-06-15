package com.example.zacatales.smartrobotapp

import android.annotation.SuppressLint
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothManager
import android.bluetooth.BluetoothSocket
import android.content.Context
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.zacatales.smartrobotapp.databinding.FragmentBluetoothBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.util.UUID

const val REQUEST_ENABLE_BT=1


class BluetoothFragment : Fragment() {


    private lateinit var binding: FragmentBluetoothBinding
    lateinit var mbluetoothAdapter: BluetoothAdapter
    var AddressDevices: ArrayAdapter<String>?=null
    var NameDevices: ArrayAdapter<String>?=null

    companion object{
        var myUUID:UUID = UUID.fromString("00001101-0000-1000-8000-00605F9B34FB")
        private var bluetoothSocket: BluetoothSocket?=null
        var isConnected: Boolean = false
        lateinit var Address:String
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        activity?.apply {
            requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        }

        binding=FragmentBluetoothBinding.inflate(inflater,container,false)
        return binding.root
    }


    @SuppressLint("MissingPermission", "ResourceType")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.actionToPrevius.setOnClickListener{
            activity?.apply {
                activity?.onBackPressedDispatcher?.onBackPressed()
            }
        }
        binding.actionToControllers.setOnClickListener {
            activity?.apply {
                //requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
                it.findNavController().navigate(R.id.action_bluetoothFragment_to_controllersFragment2)
            }
        }





        val someActivityResultLauncher= registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ){
            result ->
            if(result.resultCode == REQUEST_ENABLE_BT){
                Log.i("Main","Actividad registrada")
            }
        }



        val bluetoothAdapter: BluetoothAdapter? = BluetoothAdapter.getDefaultAdapter()
        if (bluetoothAdapter == null) {
            // Device doesn't support Bluetooth
            Toast.makeText(context,"Bluetooth no está disponible", Toast.LENGTH_LONG).show()

        }
        else{
            Toast.makeText(context,"Bluetooth está disponible", Toast.LENGTH_LONG).show()

        }

/*
        if(bluetoothAdapter!!.isEnabled){
            val pairedDevices: Set<BluetoothDevice>? = bluetoothAdapter?.bondedDevices
            AddressDevices!!.clear()
            NameDevices!!.clear()
            pairedDevices?.forEach{device ->
                val deviceName = device.name
                val deviceHardwareAddress = device.address
                AddressDevices!!.add(deviceHardwareAddress)
                NameDevices!!.add(deviceName)
            }
            binding.nameDevice.text= NameDevices.toString()

        }
        else{
            val noDevices = "Ningun dispo"
            AddressDevices!!.add(noDevices)
            NameDevices!!.add(noDevices)
            Toast.makeText(context,"Primero active bluetooth",Toast.LENGTH_LONG).show()
        }*/


        val pairedDevices: Set<BluetoothDevice>? = bluetoothAdapter?.bondedDevices

        val data:StringBuffer=StringBuffer()
        if (pairedDevices != null) {
            for(device:BluetoothDevice in pairedDevices){
                data.append(device.name+ "\r \n")

            }
        }
        if(data.isEmpty()){
            Toast.makeText(context,"Ningun dispositivo vinculado",Toast.LENGTH_LONG).show()

        }
        else{
            binding.nameDevice.text= data
        }






        /*pairedDevices?.forEach { device ->
            val deviceName = device.name
            val deviceHardwareAddress = device.address // MAC address
            binding.pruebaName.setText("name: "+ deviceName)

        }*/



        /*AddressDevices = context?.let { ArrayAdapter(it,R.layout.item_device) }
        NameDevices = context?.let { ArrayAdapter(it,R.layout.item_device) }*/




        /*if(bluetoothAdapter.isEnabled){
            val pairedDevices: Set<BluetoothDevice>?= bluetoothAdapter?.bondedDevices
            AddressDevices!!.clear()
            NameDevices!!.clear()
            pairedDevices?.forEach{device ->
                val deviceName= device.name
                val deviceHardwareAddress = device.address
                AddressDevices!!.add(deviceHardwareAddress)
                NameDevices!!.add(deviceName)

            }

            binding.pruebaName.setText("Name: "+ NameDevices)


        }else {
            val noDevice = "Ningun Dispositivo pudo ser emparejado"
            AddressDevices!!.add(noDevice)
            NameDevices!!.add(noDevice)
            Toast.makeText(context,"Primero vincule un dispositivo",Toast.LENGTH_LONG).show()

        }*/


    }






}