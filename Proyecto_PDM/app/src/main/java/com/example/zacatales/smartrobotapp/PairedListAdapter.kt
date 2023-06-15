package com.example.zacatales.smartrobotapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PairedListAdapter(var context:Context, var list:ArrayList<PairedDevicesInfo>): RecyclerView.Adapter<PairedListAdapter.PairedListHolder>(){
    class PairedListHolder(view: View):RecyclerView.ViewHolder(view){
        var deviceName = view.findViewById<TextView>(R.id.name_device)
        var macAddress = view.findViewById<TextView>(R.id.connect_device)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PairedListHolder {
        var view = LayoutInflater.from(context).inflate(R.layout.item_device,parent,false)
        return PairedListHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: PairedListHolder, position: Int) {
        holder.deviceName.text = (position+1).toString() + ". " + list[position].name
        holder.macAddress.text= "" + list[position].macAddress
    }


}



