package com.example.zacatales.smartrobotapp

import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController


class ConfimationDeleteFragment : Fragment() {
     private lateinit var btnDelete: TextView
     private lateinit var btnNotDelete: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.apply {
            requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_confimation_delete, container, false)
    }

     override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
         super.onViewCreated(view, savedInstanceState)
         bind()
         btnDelete.setOnClickListener {
             activity?.apply {
                 //requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
                 it.findNavController().navigate(R.id.action_confimationDeleteFragment_to_routeFragment)
             }
         }
         btnNotDelete.setOnClickListener {
             activity?.apply {
                 //requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
                 it.findNavController().navigate(R.id.action_confimationDeleteFragment_to_routeFragment)
             }
         }
     }

    fun bind(){
        btnDelete = view?.findViewById(R.id.action_deleteRouteButton) as TextView
        btnNotDelete = view?.findViewById(R.id.action_notdeleteRouteButton) as TextView

    }
}