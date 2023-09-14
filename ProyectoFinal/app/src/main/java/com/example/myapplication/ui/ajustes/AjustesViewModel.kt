package com.example.myapplication.ui.ajustes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AjustesViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Ajustes"
    }
    val text: LiveData<String> = _text
}