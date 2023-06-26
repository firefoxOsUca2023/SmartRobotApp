package com.example.zacatales.smartrobotapp

import android.annotation.SuppressLint
import android.bluetooth.BluetoothAdapter
import android.content.Context
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.example.zacatales.smartrobotapp.Bluetooth.`interface`.BluetoothConnectionListener
import com.example.zacatales.smartrobotapp.Bluetooth.`interface`.BluetoothManager
import com.example.zacatales.smartrobotapp.Bluetooth.recyclerview.PairedListAdapter
import com.example.zacatales.smartrobotapp.databinding.FragmentControllersBinding
import com.example.zacatales.smartrobotapp.viewmodel.RobotViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton


class ControllersFragment : Fragment() {

    private lateinit var btnToBack: FloatingActionButton
    private lateinit var btnToBluetooth: FloatingActionButton
    private lateinit var btnToRoute: FloatingActionButton
    lateinit var mbluetoothAdapter: BluetoothAdapter
    private lateinit var horn: FloatingActionButton
    private lateinit var binding: FragmentControllersBinding
    private lateinit var adapter: PairedListAdapter
    private lateinit var bluetoothManager: BluetoothManager
    private var bluetoothControlListener: BluetoothConnectionListener? = null
    private lateinit var bluetoothAdapter: BluetoothAdapter
    private lateinit var seekBar: SeekBar
    private lateinit var routeButton: FloatingActionButton
    private val viewModel: RobotViewModel by activityViewModels()


    private var buttonPressCount = 0
    var isButton1Pressed = false
    var isButton2Pressed = false

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is BluetoothConnectionListener) {
            bluetoothControlListener = context
        } else {
            throw IllegalStateException("La actividad debe implementar la interfaz BluetoothControlListener")
        }
    }


    override fun onDetach() {
        super.onDetach()
        bluetoothControlListener = null
    }

    override fun onDestroy() {
        super.onDestroy()
        bluetoothControlListener?.enviarComandoBluetooth("x")
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        activity?.apply {
            requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        }
        // Resto del código del fragmento
        // ...
        binding = FragmentControllersBinding.inflate(inflater, container, false)
        return binding.root
    }


    @SuppressLint("MissingPermission", "SuspiciousIndentation", "ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //mbluetoothAdapter = BluetoothAdapter.getDefaultAdapter()
        routeButton = binding.actionToRouteControllerFragment
        routeButton.setOnClickListener {
            it.findNavController().navigate(R.id.action_controllersFragment2_to_routeFragment)
        }

        val colorStateList = ContextCompat.getColorStateList(requireContext(), R.color.pressColor)
        val colorStateList2 = ContextCompat.getColorStateList(requireContext(), R.color.white)


        //    int orientation = yourActivityName.this.getResources().getConfiguration().orientation;
        binding.actionToPreviusControllerFragment.setOnClickListener {
            activity?.apply {
                activity?.onBackPressedDispatcher?.onBackPressed()
            }
        }

        binding.actionToBluetoothControllerFragment.setOnClickListener {

            activity?.apply {
                it.findNavController()
                    .navigate(R.id.action_controllersFragment2_to_bluetoothFragment)
            }
        }

        binding.lightsActionButton.setOnClickListener {
            buttonPressCount++
            if (buttonPressCount == 1) {
                val colorStateList =
                    ContextCompat.getColorStateList(requireContext(), R.color.pressColor)
                binding.lightsActionButton.setBackgroundTintList(colorStateList)
                bluetoothControlListener?.enviarComandoBluetooth("X")
            } else if (buttonPressCount == 2) {
                val colorStateList =
                    ContextCompat.getColorStateList(requireContext(), R.color.white)
                binding.lightsActionButton.setBackgroundTintList(colorStateList)
                bluetoothControlListener?.enviarComandoBluetooth("x")
                buttonPressCount = 0
            }
        }

        binding.hornActionButton.setOnTouchListener { view, motionEvent ->
            when (motionEvent.action) {
                MotionEvent.ACTION_DOWN -> {
                    // Se presionó el botón
                    bluetoothControlListener?.enviarComandoBluetooth("V")
                }

                MotionEvent.ACTION_UP -> {
                    // Se soltó el botón
                    bluetoothControlListener?.enviarComandoBluetooth("v")
                }
            }
            true
        }

        binding.upActionButton.setOnTouchListener { view, motionEvent ->
            when (motionEvent.action) {
                MotionEvent.ACTION_DOWN -> {
                    isButton1Pressed = true
                    binding.upActionButton.setBackgroundTintList(colorStateList)
                    viewModel.addClick("F")
                    bluetoothControlListener?.enviarComandoBluetooth("F")

                    moveForwardRight()
                    //moveForwardLeft()
                }

                MotionEvent.ACTION_UP -> {
                    bluetoothControlListener?.enviarComandoBluetooth("S")
                    binding.upActionButton.setBackgroundTintList(colorStateList2)
                    isButton1Pressed = false
                    moveForwardRight()
                    //moveForwardLeft()
                }
            }
            true
        }

        binding.rightActionButton.setOnTouchListener { view, motionEvent ->
            when (motionEvent.action) {
                MotionEvent.ACTION_DOWN -> {
                    viewModel.addClick("R")
                    binding.rightActionButton.setBackgroundTintList(colorStateList)
                    bluetoothControlListener?.enviarComandoBluetooth("R")
                    isButton2Pressed = true
                    moveForwardRight()
                }

                MotionEvent.ACTION_UP -> {
                    bluetoothControlListener?.enviarComandoBluetooth("S")
                    binding.rightActionButton.setBackgroundTintList(colorStateList2)
                    isButton2Pressed = false
                    moveForwardRight()
                }
            }
            true
        }

        binding.leftActionButton.setOnTouchListener { view, motionEvent ->
            when (motionEvent.action) {
                MotionEvent.ACTION_DOWN -> {
                    viewModel.addClick("L")
                    binding.leftActionButton.setBackgroundTintList(colorStateList)
                    bluetoothControlListener?.enviarComandoBluetooth("L")
                    isButton2Pressed = true
                    moveForwardLeft()
                }

                MotionEvent.ACTION_UP -> {
                    bluetoothControlListener?.enviarComandoBluetooth("S")
                    binding.leftActionButton.setBackgroundTintList(colorStateList2)
                    isButton2Pressed = false
                    moveForwardLeft()
                }
            }
            true
        }

        binding.backActionButton.setOnTouchListener { view, motionEvent ->
            when (motionEvent.action) {
                MotionEvent.ACTION_DOWN -> {
                    viewModel.addClick("B")
                    binding.backActionButton.setBackgroundTintList(colorStateList)
                    bluetoothControlListener?.enviarComandoBluetooth("B")
                    isButton2Pressed = true
                    moveBackwardsLeft()
                    moveBackwardsRight()
                }

                MotionEvent.ACTION_UP -> {
                    bluetoothControlListener?.enviarComandoBluetooth("S")
                    binding.backActionButton.setBackgroundTintList(colorStateList2)
                    isButton2Pressed = false
                    moveBackwardsLeft()
                    moveBackwardsRight()
                }
            }
            true
        }
        binding.seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                manejarVelocidad(progress)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                // Inicio del seguimiento del SeekBar (cuando se toca)
                manejarVelocidad(progress = 10)
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                // Fin del seguimiento del SeekBar (cuando se suelta)
                manejarVelocidad(progress = 0)
            }
        })
    }

    fun moveForwardRight() {
        if (isButton1Pressed && isButton2Pressed) {
            // Ambos botones están presionados
            // Realiza tus validaciones o acciones aquí
            bluetoothControlListener?.enviarComandoBluetooth("I")
        } else {
            // Al menos uno de los botones no está presionado
            // Realiza las acciones necesarias en caso de no cumplir la combinación
        }
    }

    fun moveForwardLeft() {
        if (isButton1Pressed && isButton2Pressed) {
            // Ambos botones están presionados
            // Realiza tus validaciones o acciones aquí
            bluetoothControlListener?.enviarComandoBluetooth("G")
        } else {
            // Al menos uno de los botones no está presionado
            // Realiza las acciones necesarias en caso de no cumplir la combinación
        }
    }

    fun moveBackwardsLeft() {
        if (isButton1Pressed && isButton2Pressed) {
            // Ambos botones están presionados
            // Realiza tus validaciones o acciones aquí
            bluetoothControlListener?.enviarComandoBluetooth("H")
        } else {
            // Al menos uno de los botones no está presionado
            // Realiza las acciones necesarias en caso de no cumplir la combinación
        }
    }

    fun moveBackwardsRight() {
        if (isButton1Pressed && isButton2Pressed) {
            // Ambos botones están presionados
            // Realiza tus validaciones o acciones aquí
            bluetoothControlListener?.enviarComandoBluetooth("J")
        } else {
            // Al menos uno de los botones no está presionado
            // Realiza las acciones necesarias en caso de no cumplir la combinación
        }
    }

    fun manejarVelocidad(progress: Int) {
        when (progress) {
            10 -> {
                bluetoothControlListener?.enviarComandoBluetooth("1")
            }

            20 -> {
                bluetoothControlListener?.enviarComandoBluetooth("2")
            }

            30 -> {
                bluetoothControlListener?.enviarComandoBluetooth("3")
            }

            40 -> {
                bluetoothControlListener?.enviarComandoBluetooth("4")
            }

            50 -> {
                bluetoothControlListener?.enviarComandoBluetooth("5")
            }

            60 -> {
                bluetoothControlListener?.enviarComandoBluetooth("6")
            }

            70 -> {
                bluetoothControlListener?.enviarComandoBluetooth("7")
            }

            80 -> {
                bluetoothControlListener?.enviarComandoBluetooth("8")
            }

            90 -> {
                bluetoothControlListener?.enviarComandoBluetooth("9")
            }

            100 -> {
                bluetoothControlListener?.enviarComandoBluetooth("q")
            }
        }
    }
}