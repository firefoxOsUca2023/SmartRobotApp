package com.example.zacatales.smartrobotapp

import android.annotation.SuppressLint
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothSocket
import android.content.Context
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.setFragmentResult
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.zacatales.smartrobotapp.Bluetooth.`interface`.BluetoothConnectionListener
import com.example.zacatales.smartrobotapp.Bluetooth.`interface`.BluetoothManager
import com.example.zacatales.smartrobotapp.Bluetooth.recyclerview.PairedListAdapter
import com.example.zacatales.smartrobotapp.Bluetooth.model.PairedDevicesInfo
import com.example.zacatales.smartrobotapp.Bluetooth.viewmodel.DeviceViewModel
import com.example.zacatales.smartrobotapp.databinding.FragmentBluetoothBinding
import java.io.IOException
import java.util.UUID

const val REQUEST_ENABLE_BT=1


class BluetoothFragment : Fragment(){


    private lateinit var adapter: PairedListAdapter
    private lateinit var binding: FragmentBluetoothBinding
    lateinit var mbluetoothAdapter: BluetoothAdapter
    //private lateinit var bluetoothManager: BluetoothManager
    private var bluetoothControlListener: BluetoothConnectionListener? = null

    private val deviceViewModel: DeviceViewModel
            by activityViewModels{
                DeviceViewModel.Factory
            }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is BluetoothConnectionListener) {
            bluetoothControlListener = context
        } else {
            throw IllegalStateException("La actividad debe implementar la interfaz BluetoothControlListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        bluetoothControlListener=null
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
            //bluetoothManager.conectarDispositivo(selectedDevice.macAddress)
            //Toast.makeText(context,selectedDevice.macAddress,Toast.LENGTH_LONG).show()
            bluetoothControlListener?.onBluetoothConnected(selectedDevice.macAddress)

        }
        binding.pairedListRV.adapter=adapter
        this
        displayDevices()
    }


    private fun showSelectedItem(device: PairedDevicesInfo){
        deviceViewModel.setSelectedDevice(device)

    }

    private fun displayDevices(){
        adapter.setData(deviceViewModel.getDevices())
        adapter.notifyDataSetChanged()
    }



}



