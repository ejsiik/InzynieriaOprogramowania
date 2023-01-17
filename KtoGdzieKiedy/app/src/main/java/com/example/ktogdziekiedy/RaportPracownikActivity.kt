package com.example.ktogdziekiedy

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import backendconnection.BackendClient
import backendconnection.BackendClient.getDoneTasksFromCurrentUser
import com.example.ktogdziekiedy.adapter.RaportPracownikAdapter
import com.example.ktogdziekiedy.model.Data
import com.example.ktogdziekiedy.model.Raport
import com.example.ktogdziekiedy.store.ReportStore
import kotlinx.android.synthetic.main.activity_raport_pracownik.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class RaportPracownikActivity : AppCompatActivity() {

    private var raportAdapter: RaportPracownikAdapter = RaportPracownikAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_raport_pracownik)

        GlobalScope.launch {
            val data = getDoneTasksFromCurrentUser(BackendClient.me().id)
            Log.d("xd", data.toString())
            runOnUiThread {
                initRecyclerView()
                raportAdapter.submitList(data)
            }
        }


        raportAdapter.onItemClick = {
            val intent = Intent(this@RaportPracownikActivity, RaportSzczegolyActivity::class.java)
            ReportStore.task = it
            startActivity(intent)
        }
    }

    private fun initRecyclerView() {
        pracownicy_recycler_view.apply {
            layoutManager = LinearLayoutManager(this@RaportPracownikActivity)
            adapter = raportAdapter
        }
    }
}