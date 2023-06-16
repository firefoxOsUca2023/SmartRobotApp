package com.example.zacatales.smartrobotapp

import android.annotation.SuppressLint
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothSocket
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.zacatales.smartrobotapp.Bluetooth.adapter.PairedListAdapter
import com.example.zacatales.smartrobotapp.Bluetooth.model.PairedDevicesInfo
import com.example.zacatales.smartrobotapp.databinding.FragmentBluetoothBinding
import java.util.UUID

const val REQUEST_ENABLE_BT=1


class BluetoothFragment : Fragment() {


    private lateinit var binding: FragmentBluetoothBinding
    lateinit var mbluetoothAdapter: BluetoothAdapter
    lateinit var pairedDeviceList:ArrayList<PairedDevicesInfo>
    var AddressDevices: ArrayAdapter<String>?=null
    var NameDevices: ArrayAdapter<String>?=null

    companion object{
        var myUUID:UUID = UUID.fromString("00001101-0000-1000-8000-00605F9B34FB")
        private var bluetoothSocket: BluetoothSocket?=null
        var isConnected: Boolean = false
        lateinit var Address:String
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

        pairedDeviceList= ArrayList()


    }



    @SuppressLint("MissingPermission")
    override fun onResume() {
        super.onResume()
        if(isBluetoothHeadsetConnected()){
            binding.pairedListRV.visibility= View.VISIBLE
            if(mbluetoothAdapter.bondedDevices.size>0){
                pairedDeviceList.clear()
                for(device in mbluetoothAdapter.bondedDevices){
                    pairedDeviceList.add(PairedDevicesInfo(device.name,device.address))
                }
                setAdapter()
            }
        }
        else {
            binding.pairedListRV.visibility= View.GONE

        }
    }

    private fun setAdapter(){
        var adapter = context?.let { PairedListAdapter(it,pairedDeviceList) }
        binding.pairedListRV.layoutManager = LinearLayoutManager(context,RecyclerView.VERTICAL,false)
        binding.pairedListRV.adapter = adapter
        this
    }

    @SuppressLint("MissingPermission", "WrongConstant")
    fun isBluetoothHeadsetConnected(): Boolean{

        mbluetoothAdapter = BluetoothAdapter.getDefaultAdapter()
        return (mbluetoothAdapter!=null && mbluetoothAdapter.isEnabled)
                //&& mbluetoothAdapter.getProfileConnectionState(BluetoothHeadset.HEADSET)== BluetoothHeadset.STATE_CONNECTED)
    }
}



