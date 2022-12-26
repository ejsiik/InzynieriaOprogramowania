package com.example.ktogdziekiedy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ktogdziekiedy.adapter.RaportPracownikAdapter
import com.example.ktogdziekiedy.model.Data
import com.example.ktogdziekiedy.model.Raport
import kotlinx.android.synthetic.main.activity_raport_pracownik.*

class RaportPracownikActivity : AppCompatActivity() {

    private lateinit var raportAdapter: RaportPracownikAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_raport_pracownik)

        initRecyclerView()
        raportAdapter.submitList(Data.raports)

        raportAdapter.onItemClick = {
            val intent = Intent(this@RaportPracownikActivity, RaportSzczegolyActivity::class.java)
            intent.putExtra("title", it)
            startActivity(intent)
        }
    }

    private fun initRecyclerView() {
        pracownicy_recycler_view.apply {
            layoutManager = LinearLayoutManager(this@RaportPracownikActivity)
            raportAdapter = RaportPracownikAdapter()
            adapter = raportAdapter
        }
    }
}