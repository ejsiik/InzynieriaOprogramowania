package com.example.ktogdziekiedy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ktogdziekiedy.adapter.MainItemAdapter
import com.example.ktogdziekiedy.databinding.ActivityRaportKategoriaBinding
import com.example.ktogdziekiedy.adapter.RaportPracownikAdapter
import com.example.ktogdziekiedy.adapter.SubSubItemAdapter
import com.example.ktogdziekiedy.model.Data
import kotlinx.android.synthetic.main.layout_raport_sub_sub_item.view.*

class RaportKategoriaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRaportKategoriaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRaportKategoriaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.kategoriaRv.adapter = MainItemAdapter(Data.cat)
    }
}