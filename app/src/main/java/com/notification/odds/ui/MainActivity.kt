package com.notification.odds.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.notification.odds.databinding.ActivityMainBinding
import com.notification.odds.viewmodels.MyViewModel


class MainActivity : AppCompatActivity() { 

    private val viewModel: MyViewModel by viewModels()
    private lateinit var _binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(_binding.root)
        _binding.vm=viewModel
        _binding.btnPredict.setOnClickListener {
            //parse data to viewmodel

            viewModel.isProgress.set(true)
            viewModel.fetchDataFromApi(_binding.etMedInc.text,_binding.etHouseAge.text,_binding.etAvgRooms.text,
                _binding.etAvgBedrms.text,_binding.etPopulation,_binding.etAveOccup,_binding.etLatitude,_binding.etLongitude)
        }
        viewModel.apiResponse.observe(this) {
            viewModel.isProgress.set(false)
            viewModel.result.set((it?.houseValue?:0.0)*100)
             Toast.makeText(this, it?.houseValue.toString()?:"0", Toast.LENGTH_SHORT).show()
        }

    }
}


