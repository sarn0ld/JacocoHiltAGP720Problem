package com.example.problem

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {

    val data = MutableStateFlow("Example")

    fun onButtonPress() {
        data.value = "Button was pressed"

    }
}