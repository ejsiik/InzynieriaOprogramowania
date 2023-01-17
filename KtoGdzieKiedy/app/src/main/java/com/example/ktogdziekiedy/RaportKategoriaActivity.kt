package com.example.ktogdziekiedy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import backendconnection.BackendClient
import com.example.ktogdziekiedy.adapter.MainItemAdapter
import com.example.ktogdziekiedy.databinding.ActivityRaportKategoriaBinding
import com.example.ktogdziekiedy.model.Data


class RaportKategoriaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRaportKategoriaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRaportKategoriaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.kategoriaRv.adapter = MainItemAdapter(this@RaportKategoriaActivity, Data.cat)
    }
}