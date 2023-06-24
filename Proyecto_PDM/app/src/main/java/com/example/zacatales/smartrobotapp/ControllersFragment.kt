package com.example.zacatales.smartrobotapp

import android.annotation.SuppressLint
import android.bluetooth.BluetoothAdapter
import android.content.Context
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.zacatales.smartrobotapp.Bluetooth.`interface`.BluetoothConnectionListener
import com.example.zacatales.smartrobotapp.Bluetooth.`interface`.BluetoothManager
import com.example.zacatales.smartrobotapp.Bluetooth.recyclerview.PairedListAdapter
import com.example.zacatales.smartrobotapp.databinding.FragmentControllersBinding
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

    private var buttonPressCount = 0

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


    @SuppressLint("MissingPermission", "SuspiciousIndentation")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //mbluetoothAdapter = BluetoothAdapter.getDefaultAdapter()


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

            binding.hornActionButton.setOnClickListener {
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
            }






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