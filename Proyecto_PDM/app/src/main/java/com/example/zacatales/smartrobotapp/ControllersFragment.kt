package com.example.zacatales.smartrobotapp

import android.annotation.SuppressLint
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothHeadset
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


class ControllersFragment : Fragment(){

    private lateinit var btnToBack: FloatingActionButton
    private lateinit var btnToBluetooth: FloatingActionButton
    private lateinit var btnToRoute: FloatingActionButton
    lateinit var mbluetoothAdapter: BluetoothAdapter
    private lateinit var horn: FloatingActionButton
    private lateinit var binding: FragmentControllersBinding
    private lateinit var adapter: PairedListAdapter
    //private lateinit var bluetoothManager: BluetoothManager
    private var bluetoothControlListener: BluetoothConnectionListener? = null

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



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
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
        mbluetoothAdapter = BluetoothAdapter.getDefaultAdapter()
        if(mbluetoothAdapter.getProfileConnectionState(BluetoothHeadset.HEADSET)== BluetoothAdapter.STATE_DISCONNECTED){
            Toast.makeText(context,"Conectate",Toast.LENGTH_SHORT).show()
        }

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
        binding.lightsActionButton.setOnClickListener {
            bluetoothControlListener?.enviarComandoBluetooth("x")
        }

        binding.hornActionButton.setOnClickListener {
            bluetoothControlListener?.enviarComandoBluetooth("X")
            //bluetoothControlListener?.onCommandBluetooth("X")
            //bluetoothManager.enviarComando("X")
        }
        binding.routeActionButton.setOnClickListener {
            //bluetoothManager.enviarComando("X")
            bluetoothControlListener?.onBluetoothDisconnected()
        }
        binding.upActionButton.setOnClickListener {
            bluetoothControlListener?.enviarComandoBluetooth("F")
        }


    }


}