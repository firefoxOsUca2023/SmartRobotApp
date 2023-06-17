package com.example.zacatales.smartrobotapp.Bluetooth.adapter

import android.widget.ExpandableListView.OnChildClickListener
import androidx.recyclerview.widget.RecyclerView
import com.example.zacatales.smartrobotapp.Bluetooth.model.PairedDevicesInfo
import com.example.zacatales.smartrobotapp.databinding.ItemDeviceBinding

class PairedListViewHolder(private val binding: ItemDeviceBinding): RecyclerView.ViewHolder(binding.root)
{
    fun bind(device: PairedDevicesInfo, clickListener: (PairedDevicesInfo) -> Unit){
        binding.nameDevice.text = device.name
        binding.connectDevice.text = device.macAddress
        binding.deviceItemDeviceCard.setOnClickListener {
            clickListener(device)
        }
    }
}