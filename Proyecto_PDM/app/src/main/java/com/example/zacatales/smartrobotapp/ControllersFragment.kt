package com.example.zacatales.smartrobotapp

import android.content.pm.ActivityInfo
import android.content.res.ColorStateList
import android.content.res.Configuration
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.google.android.material.floatingactionbutton.FloatingActionButton


class ControllersFragment : Fragment() {

    private lateinit var btnToBack: FloatingActionButton
    private lateinit var btnToBluetooth: FloatingActionButton
    private lateinit var btnToRoute: FloatingActionButton
    private lateinit var lightsActionBtn: FloatingActionButton
    private lateinit var hornActionBtn: FloatingActionButton
    private var isButtonPressed = false
    private var isButtonPressed2 = false



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //resources.configuration.orientation = Configuration.ORIENTATION_LANDSCAPE
        activity?.apply {
            requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        }
    }




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        /*val callback = object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                findNavController().popBackStack()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(callback)*/
        // Inflate the layout for this fragment
        activity?.apply {
            requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        }
        return inflater.inflate(R.layout.fragment_controllers, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bin()
        //    int orientation = yourActivityName.this.getResources().getConfiguration().orientation;
        btnToBack.setOnClickListener {
            activity?.apply {
                activity?.onBackPressedDispatcher?.onBackPressed()
            }
        }
        btnToBluetooth.setOnClickListener {

            activity?.apply {
                it.findNavController().navigate(R.id.action_controllersFragment2_to_bluetoothFragment)
            }
        }
        btnToRoute.setOnClickListener {
            activity?.apply {
                it.findNavController().navigate(R.id.action_controllersFragment2_to_routeFragment)
            }
        }

        lightsActionBtn.setOnClickListener{
            isButtonPressed = !isButtonPressed // Cambia el estado del botón

            if (isButtonPressed) {
                lightsActionBtn.backgroundTintList = ColorStateList.valueOf(Color.YELLOW) // Cambia el color cuando el botón está presionado
            } else {
                lightsActionBtn.backgroundTintList= ColorStateList.valueOf(Color.WHITE) // Cambia el color cuando el botón no está presionado
            }
        }

        hornActionBtn.setOnClickListener{
            isButtonPressed2 = !isButtonPressed2 // Cambia el estado del botón

            if (isButtonPressed2) {
                hornActionBtn.backgroundTintList = ColorStateList.valueOf(Color.YELLOW) // Cambia el color cuando el botón está presionado
            } else {
                hornActionBtn.backgroundTintList= ColorStateList.valueOf(Color.WHITE) // Cambia el color cuando el botón no está presionado
            }
        }



        /*requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                activity?.apply {
                    requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

                }
            }

        })*/

    }


    fun bin(){
        btnToBack = view?.findViewById(R.id.action_to_previus_controllerFragment) as FloatingActionButton
        btnToBluetooth = view?.findViewById(R.id.action_to_bluetooth_ControllerFragment) as FloatingActionButton
        btnToRoute = view?.findViewById(R.id.route_actionButton) as FloatingActionButton
        lightsActionBtn = view?.findViewById(R.id.lights_actionButton) as FloatingActionButton
        hornActionBtn = view?.findViewById(R.id.horn_actionButton) as FloatingActionButton
    }


}