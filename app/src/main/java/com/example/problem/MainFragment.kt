package com.example.problem

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.asLiveData
import com.example.problem.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment(R.layout.fragment_main) {

    private val viewModel: MainViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.data.asLiveData().observe(viewLifecycleOwner) {
            view.findViewById<TextView>(R.id.message).text = it
        }
        view.findViewById<Button>(R.id.button).setOnClickListener { onButtonPress() }
    }

    private fun onButtonPress() {
        viewModel.onButtonPress()
    }
}