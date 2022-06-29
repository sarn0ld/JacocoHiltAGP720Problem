package com.example.problem

import org.junit.Assert.assertEquals
import org.junit.Test

internal class MainViewModelTest {

    @Test
    fun getData() {
        // Given a fresh ViewModel
        val viewModel = MainViewModel()
        // When getting the data
        val result = viewModel.data.value
        // Then it should be in initial state
        assertEquals("Example", result)
    }

    @Test
    fun onButtonPress() {
        // Given a ViewModel with button pressed
        val viewModel = MainViewModel()
        viewModel.onButtonPress()
        // When getting the data
        val result = viewModel.data.value
        // Then it should be in initial state
        assertEquals("Button was pressed", result)
    }
}