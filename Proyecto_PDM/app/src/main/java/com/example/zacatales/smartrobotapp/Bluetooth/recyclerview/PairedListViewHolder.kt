package com.example.zacatales.smartrobotapp.Bluetooth.recyclerview

import android.annotation.SuppressLint
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothSocket
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.zacatales.smartrobotapp.Bluetooth.model.PairedDevicesInfo
import com.example.zacatales.smartrobotapp.BluetoothFragment
import com.example.zacatales.smartrobotapp.MainActivity
import com.example.zacatales.smartrobotapp.databinding.ItemDeviceBinding
import java.io.IOException
import java.util.UUID

class PairedListViewHolder(private val binding: ItemDeviceBinding): RecyclerView.ViewHolder(binding.root)
{
    fun bind(device: PairedDevicesInfo, clickListener: (PairedDevicesInfo) -> Unit){
        binding.nameDevice.text = device.name
        binding.connectDevice.text = ""
        binding.deviceItemDeviceCard.setOnClickListener {
            clickListener(device)
        }
    }
}