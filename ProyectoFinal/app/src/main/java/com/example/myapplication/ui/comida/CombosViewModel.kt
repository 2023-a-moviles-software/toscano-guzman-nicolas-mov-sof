package com.example.myapplication.ui.comida

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CombosViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Combos"
    }
    val text: LiveData<String> = _text
}