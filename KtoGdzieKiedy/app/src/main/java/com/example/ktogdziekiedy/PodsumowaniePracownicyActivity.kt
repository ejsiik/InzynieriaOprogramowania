package com.example.ktogdziekiedy

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import backendconnection.BackendClient
import com.example.ktogdziekiedy.adapter.PodsumowaniePracownikAdapter
import com.example.ktogdziekiedy.store.ReportStore
import kotlinx.android.synthetic.main.activity_raport_pracownik.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class PodsumowaniePracownicyActivity : AppCompatActivity() {

    private var pracownikAdapter: PodsumowaniePracownikAdapter = PodsumowaniePracownikAdapter()
    //var data: List<Task>? =null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_podsumowanie_pracownicy)

        val users = mutableListOf<String>()

        GlobalScope.launch {
            val data = BackendClient.getDoneTasksFromAllUsers()
            data.forEach {
                users.add(it.userId.toString())
            }
            val result = users.distinct()
            runOnUiThread {
                initRecyclerView()
                pracownikAdapter.submitList(result)
            }

            pracownikAdapter.onItemClick = {
                userId ->
                val intent = Intent(this@PodsumowaniePracownicyActivity, PodsumowanieDanegoActivity::class.java)
                ReportStore.userTask = data.filter {
                    it.userId.toString() == userId
                }
                intent.putExtra("id", userId)
                startActivity(intent)
            }
        }
    }

    private fun initRecyclerView() {
        pracownicy_recycler_view.apply {
            layoutManager = LinearLayoutManager(this@PodsumowaniePracownicyActivity)
            adapter = pracownikAdapter
        }
    }
}