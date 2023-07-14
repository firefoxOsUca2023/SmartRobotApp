package com.example.zacatales.smartrobotapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RobotViewModel : ViewModel() {
    // MutableLiveData que va a guardar los clicks
    private val _clicks = MutableLiveData<List<String>>()
    // LiveData para exponer los datos de forma segura
    val clicks: LiveData<List<String>> get() = _clicks
    private val clickList = ArrayList<String>()

    // Variable que controlará si los datos se han eliminado desde RouteView
    private val _isClearedFromRoute = MutableLiveData<Boolean>(false)
    val isClearedFromRoute: LiveData<Boolean> get() = _isClearedFromRoute

    // Función para agregar un click a la lista
    fun addClick(click: String) {
        clickList.add(click)
        _clicks.value = clickList
    }

    // Función para borrar los clicks
    fun clearClicks() {
        clickList.clear()
        _clicks.value = clickList
        _isClearedFromRoute.value = false  // restablecemos el estado después de borrar
    }

    // Función para establecer que los clicks han sido borrados desde RouteView
    fun setClicksClearedFromRoute() {
        _isClearedFromRoute.value = true
    }
}
