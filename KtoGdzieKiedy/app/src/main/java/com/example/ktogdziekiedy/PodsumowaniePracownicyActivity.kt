package com.example.ktogdziekiedy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ktogdziekiedy.adapter.PodsumowaniePracownikAdapter
import com.example.ktogdziekiedy.model.Data
import kotlinx.android.synthetic.main.activity_raport_pracownik.*

class PodsumowaniePracownicyActivity : AppCompatActivity() {

    private lateinit var pracownikAdapter: PodsumowaniePracownikAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_podsumowanie_pracownicy)

        initRecyclerView()
        pracownikAdapter.submitList(Data.workers)

        pracownikAdapter.onItemClick = {
            val intent = Intent(this@PodsumowaniePracownicyActivity, RaportSzczegolyActivity::class.java)
            startActivity(intent)
        }
    }

    private fun initRecyclerView() {
        pracownicy_recycler_view.apply {
            layoutManager = LinearLayoutManager(this@PodsumowaniePracownicyActivity)
            pracownikAdapter = PodsumowaniePracownikAdapter()
            adapter = pracownikAdapter
        }
    }
}