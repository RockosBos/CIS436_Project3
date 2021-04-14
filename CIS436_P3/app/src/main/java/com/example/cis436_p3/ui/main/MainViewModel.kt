package com.example.cis436_p3.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    val currentWeather: MutableLiveData<String> by lazy{
        MutableLiveData<String>()
    }
}