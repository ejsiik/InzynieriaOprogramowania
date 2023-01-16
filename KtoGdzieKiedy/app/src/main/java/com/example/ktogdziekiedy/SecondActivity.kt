package com.example.ktogdziekiedy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import backendconnection.BackendClient
import backendconnection.Task
import com.example.ktogdziekiedy.adapter.RunningTasksAdapter
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val recyclerview = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerview.layoutManager = LinearLayoutManager(this)

        /*for (i in 1..5) {
            data.add(ItemsViewModel( "Item " + i))
        }*/
        val data = ArrayList<ItemsViewModel>()
        val bundle = intent.extras

        if (bundle != null) {
                data.add(ItemsViewModel("Task = ${bundle.getString("name")}"))
            } else {
            Log.e("Error", "Bundle is null.")}
        //val data = ArrayList<String>()
        /*GlobalScope.launch {
            val tasks = BackendClient.runningTasks()
            tasks.forEach {
                Log.d(it.name,"xx")

            }
            // This will pass the ArrayList to our Adapter
            val adapter = RunningTasksAdapter(tasks)

            // Setting the Adapter with the recyclerview
            recyclerview.adapter = adapter
        }*/

    }
}




