package com.example.zacatales.smartrobotapp

import android.annotation.SuppressLint
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothSocket
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
import com.example.zacatales.smartrobotapp.Bluetooth.model.PairedDevicesInfo
import com.example.zacatales.smartrobotapp.Bluetooth.recyclerview.PairedListAdapter
import com.example.zacatales.smartrobotapp.Bluetooth.viewmodel.DeviceViewModel
import com.example.zacatales.smartrobotapp.databinding.FragmentControllersBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.io.IOException
import java.util.UUID


class ControllersFragment : Fragment() {

    private lateinit var btnToBack: FloatingActionButton
    private lateinit var btnToBluetooth: FloatingActionButton
    private lateinit var btnToRoute: FloatingActionButton
    lateinit var mbluetoothAdapter: BluetoothAdapter
    private lateinit var horn: FloatingActionButton
    private lateinit var binding: FragmentControllersBinding
    private lateinit var adapter: PairedListAdapter

    private val deviceViewModel: DeviceViewModel
            by activityViewModels{
                DeviceViewModel.Factory
            }


    companion object{
        var myUUID: UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB")
        private var bluetoothSocket: BluetoothSocket?=null
        var isConnected: Boolean = false
        lateinit var Address:String
    }

    @SuppressLint("MissingPermission")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //resources.configuration.orientation = Configuration.ORIENTATION_LANDSCAPE
        activity?.apply {
            requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        }

    }


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
        setFragmentListener()

        return binding.root
    }
    private fun setFragmentListener(){
        setFragmentResultListener("key"){key,bundle ->
            Address = bundle.getString("address").toString()

        }

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

    }

    private fun sendCommand(input: String){
        if(bluetoothSocket != null){
            try {
                bluetoothSocket!!.outputStream.write(input.toByteArray())
            }catch (e: IOException){
                e.printStackTrace()
            }
        }
    }


}