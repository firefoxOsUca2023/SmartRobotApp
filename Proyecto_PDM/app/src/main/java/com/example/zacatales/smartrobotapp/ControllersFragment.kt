package com.example.zacatales.smartrobotapp

import android.annotation.SuppressLint
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothSocket
import android.content.Context
import android.content.Intent
import android.content.pm.ActivityInfo
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.zacatales.smartrobotapp.Bluetooth.`interface`.BluetoothConnectionListener
import com.example.zacatales.smartrobotapp.Bluetooth.`interface`.BluetoothManager
import com.example.zacatales.smartrobotapp.Bluetooth.model.PairedDevicesInfo
import com.example.zacatales.smartrobotapp.Bluetooth.recyclerview.PairedListAdapter
import com.example.zacatales.smartrobotapp.Bluetooth.viewmodel.DeviceViewModel
import com.example.zacatales.smartrobotapp.databinding.FragmentControllersBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.io.IOException
import java.util.UUID


class ControllersFragment : Fragment(),BluetoothConnectionListener{

    private lateinit var btnToBack: FloatingActionButton
    private lateinit var btnToBluetooth: FloatingActionButton
    private lateinit var btnToRoute: FloatingActionButton
    lateinit var mbluetoothAdapter: BluetoothAdapter
    private lateinit var horn: FloatingActionButton
    private lateinit var binding: FragmentControllersBinding
    private lateinit var adapter: PairedListAdapter
    private lateinit var bluetoothManager: BluetoothManager
    private var bluetoothControlListener: BluetoothConnectionListener? = null




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bluetoothManager = BluetoothManager(requireContext(),this)


    }








/*
    @SuppressLint("MissingPermission")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //resources.configuration.orientation = Configuration.ORIENTATION_LANDSCAPE
        activity?.apply {
            requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        }
        /*val datos = arguments?.getString("datos")

        Toast.makeText(context,"mac: "+ datos,Toast.LENGTH_LONG).show()


        if(datos!=null){
           try {
               if(bluetoothSocket == null || !isConnected){
                   mbluetoothAdapter = BluetoothAdapter.getDefaultAdapter()
                   val Device: BluetoothDevice = mbluetoothAdapter.getRemoteDevice(datos)
                   bluetoothSocket = Device.createRfcommSocketToServiceRecord(myUUID)
                   BluetoothAdapter.getDefaultAdapter().cancelDiscovery()
                   bluetoothSocket!!.connect()
               }
               Toast.makeText(context,"Conexión exitosa",Toast.LENGTH_LONG).show()

           }catch (e: IOException){
               e.printStackTrace()
               Toast.makeText(context,"Error de conexión",Toast.LENGTH_LONG).show()
           }
       }*/


    }*/


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        /*val callback = object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                findNavController().popBackStack()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(callback)*/
        // Inflate the layout for this fragment
        activity?.apply {
            requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        }
        binding= FragmentControllersBinding.inflate(inflater,container,false)

        return binding.root
    }


    @SuppressLint("MissingPermission")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //    int orientation = yourActivityName.this.getResources().getConfiguration().orientation;
        binding.actionToPreviusControllerFragment.setOnClickListener {
            activity?.apply {
                activity?.onBackPressedDispatcher?.onBackPressed()
            }
        }

        binding.actionToBluetoothControllerFragment.setOnClickListener {

            activity?.apply {
                it.findNavController().navigate(R.id.action_controllersFragment2_to_bluetoothFragment)
            }
        }
        binding.routeActionButton.setOnClickListener {
            activity?.apply {
                it.findNavController().navigate(R.id.action_controllersFragment2_to_routeFragment)
            }
        }

        //val deviceAddress = "00:20:02:20:11:C8" // Reemplazar con la dirección MAC del dispositivo
        //bluetoothManager.connectToDevice(deviceAddress)

        binding.hornActionButton.setOnClickListener {
            //bluetoothControlListener?.enviarComandoBluetooth("X")
            //bluetoothControlListener?.onCommandBluetooth("X")
            bluetoothManager.enviarComando("X")
        }
        binding.routeActionButton.setOnClickListener {
            //bluetoothManager.enviarComando("X")
            //bluetoothControlListener?.enviarComandoBluetooth("x")
        }


    }

    override fun onBluetoothConnected(address: String) {
    }

    override fun enviarComandoBluetooth(comando: String) {
    }

    override fun onBluetoothConnectionError(error: String) {
    }

    override fun onBluetoothDisconnected() {
    }


}