package com.example.zacatales.smartrobotapp

import android.bluetooth.BluetoothManager
import android.content.Context
import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.example.zacatales.smartrobotapp.databinding.FragmentBluetoothBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.security.Provider.Service

class BluetoothFragment : Fragment() {

    private lateinit var btnToControllers: FloatingActionButton
    private lateinit var btnToWelcome: FloatingActionButton
    private lateinit var binding: FragmentBluetoothBinding


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
    }

    fun scan(view: View){
        val mb=((Context.BLUETOOTH_SERVICE) as BluetoothManager).adapter
    }


}