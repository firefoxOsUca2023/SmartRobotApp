package com.example.zacatales.smartrobotapp

import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.google.android.material.floatingactionbutton.FloatingActionButton

class RouteFragment : Fragment() {
   private lateinit var btnToBack: FloatingActionButton
   private lateinit var btnToDelete: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_route, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bind()
        btnToBack.setOnClickListener {
            activity?.apply {
                requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
                it.findNavController().navigate(R.id.action_routeFragment_to_controllersFragment2)
            }
        }
        btnToDelete.setOnClickListener {
            activity?.apply {
                requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
                it.findNavController().navigate(R.id.action_routeFragment_to_confimationDeleteFragment)
            }
        }
    }

    fun bind(){
        btnToBack = view?.findViewById(R.id.action_to_previus_routeFragment) as FloatingActionButton
        btnToDelete = view?.findViewById(R.id.action_deleteRoute) as FloatingActionButton
    }
}