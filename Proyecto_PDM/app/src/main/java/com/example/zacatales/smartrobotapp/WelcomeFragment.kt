package com.example.zacatales.smartrobotapp

import android.app.Activity
import android.app.Activity.RESULT_OK
import android.app.Instrumentation.ActivityResult
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothManager
import android.content.Intent
import android.content.pm.ActivityInfo
import android.content.res.Configuration
import android.os.Build
import android.os.Build.VERSION
import android.os.Bundle
import android.os.ProxyFileDescriptorCallback
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.navigation.findNavController
import com.example.hp.bluetoothjhr.BluetoothJhr
import com.example.zacatales.smartrobotapp.databinding.FragmentWelcomeBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton

class WelcomeFragment : Fragment() {

    private lateinit var btnToControllers: FloatingActionButton
    private lateinit var btnToBluetooth: FloatingActionButton
    private lateinit var binding: FragmentWelcomeBinding
    private lateinit var list_D: ListView
    private var btPermissions = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        //FOR THE APP CLOSE ALONE IN MAIN FRAGMENT
        requireActivity().onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                requireActivity().finish()
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        activity?.apply {
            requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        }
        binding = FragmentWelcomeBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //val bluetoothadapter:BluetoothAdapter
        //bluetoothadapter= BluetoothAdapter.getDefaultAdapter()
        binding.actionToBluetooth.setOnClickListener{
            //val intent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
            //startActivityForResult(intent,1)
            //val bluetoothAdapter: BluetoothAdapter? = BluetoothAdapter.getDefaultAdapter()
            it.findNavController().navigate(R.id.action_welcomeFragment_to_bluetoothFragment)


        }
        binding.actionToControllers.setOnClickListener {
            activity?.apply {
                //requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
                it.findNavController().navigate(R.id.action_welcomeFragment_to_controllersFragment2)
            }
        }

    }

}
