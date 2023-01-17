package com.example.ktogdziekiedy

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import backendconnection.BackendClient
import com.example.ktogdziekiedy.adapter.RunningTasksAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val recyclerview = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerview.layoutManager = LinearLayoutManager(this)

        GlobalScope.launch {
            val tasks = BackendClient.runningTasks()
            // This will pass the ArrayList to our Adapter
            val adapter = RunningTasksAdapter(tasks, this@SecondActivity)

            // Setting the Adapter with the recyclerview
            withContext(Dispatchers.Main) {
                recyclerview.adapter = adapter
            }
        }

    }
}




