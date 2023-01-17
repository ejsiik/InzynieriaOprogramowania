package com.example.ktogdziekiedy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import backendconnection.BackendClient
import backendconnection.Task
import com.example.ktogdziekiedy.adapter.DanegoAdapter
import com.example.ktogdziekiedy.store.ReportStore
import kotlinx.android.synthetic.main.activity_raport_pracownik.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class PodsumowanieDanegoActivity : AppCompatActivity() {

    private var pracownikAdapter: DanegoAdapter = DanegoAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_podsumowanie_danego)

        val task = ReportStore.userTask

        //var id = intent.extras!!.getString("id")

        initRecyclerView()
        pracownikAdapter.submitList(task!!)
        pracownikAdapter.onItemClick = {
            val intent = Intent(this@PodsumowanieDanegoActivity, RaportSzczegolyActivity::class.java)
            ReportStore.task = it
            startActivity(intent)
        }

    }

    private fun initRecyclerView() {
        pracownicy_recycler_view.apply {
            layoutManager = LinearLayoutManager(this@PodsumowanieDanegoActivity)
            adapter = pracownikAdapter
        }
    }
}