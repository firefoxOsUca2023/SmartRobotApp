package com.example.zacatales.smartrobotapp

import android.annotation.SuppressLint
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothSocket
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.setFragmentResult
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.zacatales.smartrobotapp.Bluetooth.recyclerview.PairedListAdapter
import com.example.zacatales.smartrobotapp.Bluetooth.model.PairedDevicesInfo
import com.example.zacatales.smartrobotapp.Bluetooth.viewmodel.DeviceViewModel
import com.example.zacatales.smartrobotapp.databinding.FragmentBluetoothBinding
import java.io.IOException
import java.util.UUID

const val REQUEST_ENABLE_BT=1


class BluetoothFragment : Fragment() {

    private val deviceViewModel: DeviceViewModel
        by activityViewModels{
            DeviceViewModel.Factory
        }

    private lateinit var adapter: PairedListAdapter
    private lateinit var binding: FragmentBluetoothBinding
    lateinit var mbluetoothAdapter: BluetoothAdapter

    companion object{
        var myUUID:UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB")
        var bluetoothSocket: BluetoothSocket?=null
        var isConnected: Boolean = false
        var Address:String = ""
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
        setRecyclerView(view)

    }


    @SuppressLint("MissingPermission")
    private fun setRecyclerView(view: View){
        binding.pairedListRV.layoutManager = LinearLayoutManager(view.context)
        adapter = PairedListAdapter{
            selectedDevice ->
            showSelectedItem(selectedDevice)
            //Toast.makeText(context,selectedDevice.name,Toast.LENGTH_LONG).show()
        }
        binding.pairedListRV.adapter=adapter
        this
        displayDevices()
    }

    @SuppressLint("MissingPermission")
    private fun showSelectedItem(device: PairedDevicesInfo){
        deviceViewModel.setSelectedDevice(device)
        setFragmentResult("key", bundleOf("address" to device.macAddress))
        /*try {
            if(bluetoothSocket == null || !isConnected){
                mbluetoothAdapter = BluetoothAdapter.getDefaultAdapter()
                val Device: BluetoothDevice = mbluetoothAdapter.getRemoteDevice(device.macAddress)
                bluetoothSocket = Device.createRfcommSocketToServiceRecord(myUUID)
                BluetoothAdapter.getDefaultAdapter().cancelDiscovery()
                bluetoothSocket!!.connect()
            }
            Toast.makeText(context,"Conexión exitosa",Toast.LENGTH_LONG).show()
            Address = device.macAddress
            val intent = Intent(context, MainActivity::class.java)
            intent.putExtra("key", Address)

        }catch (e: IOException){
            e.printStackTrace()
            Toast.makeText(context,"Error de conexión",Toast.LENGTH_LONG).show()
        }*/

    }

    private fun displayDevices(){
        adapter.setData(deviceViewModel.getDevices())
        adapter.notifyDataSetChanged()
    }
    private fun sendCommand(input: String){
        if(bluetoothSocket != null){
            try {
                bluetoothSocket!!.outputStream.write(input.toByteArray())
            }catch (e: IOException){
            }
        }
    }
    private fun disconnect(){
        if(bluetoothSocket!=null){
            try {
                bluetoothSocket!!.close()
                bluetoothSocket= null
                isConnected = false
            }catch (e: IOException){
                e.printStackTrace()
            }
        }
    }
}



