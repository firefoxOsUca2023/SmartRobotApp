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
        binding = FragmentControllersBinding.inflate(inflater,container,false)
        return binding.root
    }


    @SuppressLint("MissingPermission", "SuspiciousIndentation", "ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //mbluetoothAdapter = BluetoothAdapter.getDefaultAdapter()
            routeButton = binding.actionToRouteControllerFragment
        routeButton.setOnClickListener {
            it.findNavController().navigate(R.id.action_controllersFragment2_to_routeFragment)
            // Verificamos si los clicks se borraron en RouteView
            if(viewModel.isClearedFromRoute.value == true) {
                viewModel.clearClicks()
            }
        }



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
                    val colorStateList = ContextCompat.getColorStateList(requireContext(), R.color.pressColor)
                    binding.lightsActionButton.setBackgroundTintList(colorStateList)
                    bluetoothControlListener?.enviarComandoBluetooth("X")
                } else if (buttonPressCount == 2) {
                    val colorStateList = ContextCompat.getColorStateList(requireContext(), R.color.white)
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
                    viewModel.addClick("F")
                    bluetoothControlListener?.enviarComandoBluetooth("F")

                    moveForwardRight()
                    //moveForwardLeft()
                }
                MotionEvent.ACTION_UP -> {
                    bluetoothControlListener?.enviarComandoBluetooth("S")
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
                    bluetoothControlListener?.enviarComandoBluetooth("R")
                    isButton2Pressed = true
                    moveForwardRight()
                }
                MotionEvent.ACTION_UP -> {
                    bluetoothControlListener?.enviarComandoBluetooth("S")
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
                    bluetoothControlListener?.enviarComandoBluetooth("L")
                    isButton2Pressed = true
                    moveForwardLeft()
                }
                MotionEvent.ACTION_UP -> {
                    bluetoothControlListener?.enviarComandoBluetooth("S")
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
                    bluetoothControlListener?.enviarComandoBluetooth("B")
                    isButton2Pressed = true
                    moveBackwardsLeft()
                    moveBackwardsRight()
                }
                MotionEvent.ACTION_UP -> {
                    bluetoothControlListener?.enviarComandoBluetooth("S")
                    isButton2Pressed = false
                    moveBackwardsLeft()
                    moveBackwardsRight()
                }
            }
            true
        }




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

}





/*val datos = arguments?.getString("datos")

 private fun showToast(message: String) {
        requireActivity().runOnUiThread {
            Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
        }
    }

   Toast.makeText(context,"mac: "+ datos,Toast.LENGTH_LONG).show()


   if(datos!=null){
      try {
          if(bluetoothSocket == null || !isConnected){
              mbluetoothAdapter = BluetoothAdapter.getDefaultAdapter()
              val Device: BluetoothDevice = mbluetoothAdapter.getRemoteDevice(datos)
              bluetoothSocket = Device.createRfcommSocketToServiceRecord(myUUID)
              BluetoothAdapter.getDefaultAdapter().cancelDiscovery()
              bluetoothSocket!!.connect()
          }
          Toast.makeText(context,"Conexión exitosa",Toast.LENGTH_LONG).show()

      }catch (e: IOException){
          e.printStackTrace()
          Toast.makeText(context,"Error de conexión",Toast.LENGTH_LONG).show()
      }
  }*/




/*button.setOnTouchListener { view, motionEvent ->
when (motionEvent.action) {
MotionEvent.ACTION_DOWN -> {
    // Se presionó el botón
    val data = "¡Hola, dispositivo Bluetooth!"
    try {
        val outputStream: OutputStream? = socket?.outputStream
        outputStream?.write(data.toByteArray())
    } catch (e: IOException) {
        // Manejo de errores
    }
}
MotionEvent.ACTION_UP -> {
    // Se soltó el botón
}
}
true
}*/







/*binding.hornActionButton.setOnClickListener {
    buttonPressCount++
    if (buttonPressCount == 1) {
        val colorStateList = ContextCompat.getColorStateList(requireContext(), R.color.pressColor)
        binding.hornActionButton.setBackgroundTintList(colorStateList)
        bluetoothControlListener?.enviarComandoBluetooth("V")
    } else if (buttonPressCount == 2) {
        val colorStateList = ContextCompat.getColorStateList(requireContext(), R.color.white)
        binding.hornActionButton.setBackgroundTintList(colorStateList)
        bluetoothControlListener?.enviarComandoBluetooth("v")
        buttonPressCount = 0
    }
}*/









//BluetoothAdapter.getDefaultAdapter().getProfileConnectionState(BluetoothProfile.HEALTH) == BluetoothProfile.STATE_CONNECTED

/*if(mbluetoothAdapter.getProfileConnectionState(BluetoothHeadset.HEADSET)== BluetoothAdapter.STATE_DISCONNECTED){
    Toast.makeText(context,"Conectate",Toast.LENGTH_SHORT).show()
}*/

//bluetoothManager = BluetoothManager(requireContext(),this)
//bluetoothManager.setListener(this)
/*if(!bluetoothManager.isConnected){
    binding.lightsActionButton.setOnClickListener {
        Toast.makeText(context, "message", Toast.LENGTH_SHORT).show()
    }

    binding.hornActionButton.setOnClickListener {
        Toast.makeText(context, "message", Toast.LENGTH_SHORT).show()
        //bluetoothControlListener?.onCommandBluetooth("X")
        //bluetoothManager.enviarComando("X")
    }
    binding.upActionButton.setOnClickListener {
        //bluetoothControlListener?.onBluetoothDisconnected()
        Toast.makeText(context, "message", Toast.LENGTH_SHORT).show()
    }

    binding.actionToPreviusControllerFragment.setOnClickListener {
        activity?.apply {
            activity?.onBackPressedDispatcher?.onBackPressed()
        }
    }

    binding.actionToBluetoothControllerFragment.setOnClickListener {

        activity?.apply {
            it.findNavController().navigate(R.id.action_controllersFragment2_to_bluetoothFragment)
        }
    }
}*/