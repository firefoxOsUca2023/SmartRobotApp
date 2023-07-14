package com.example.zacatales.smartrobotapp

import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.zacatales.smartrobotapp.view.RouteView
import com.example.zacatales.smartrobotapp.viewmodel.RobotViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class RouteFragment : Fragment() {
   private lateinit var btnToBack: FloatingActionButton
   private lateinit var btnToDelete: FloatingActionButton
    private val viewModel: RobotViewModel by activityViewModels()
    private lateinit var routeView: RouteView
    private lateinit var deleteButton: FloatingActionButton



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        activity?.apply {
            requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        }
        return inflater.inflate(R.layout.fragment_route, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        routeView = view.findViewById(R.id.routeView)

        viewModel.clicks.observe(viewLifecycleOwner, { clicks ->
            routeView.setRouteList(clicks)

        })
        bind()

        btnToBack.setOnClickListener {
            activity?.apply {
                //requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
                activity?.apply {
                    activity?.onBackPressedDispatcher?.onBackPressed()
                }
            }
        }
        btnToDelete.setOnClickListener {
            activity?.apply {
                //requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
                it.findNavController().navigate(R.id.action_routeFragment_to_confimationDeleteFragment)
                routeView.clearRoute()
                // Aquí indicamos que se han borrado los datos de RouteView
                viewModel.setClicksClearedFromRoute()
            }
        }

    }

    fun bind(){
        btnToBack = view?.findViewById(R.id.action_to_previus_routeFragment) as FloatingActionButton
        btnToDelete = view?.findViewById(R.id.action_deleteRoute) as FloatingActionButton
    }
}