package com.example.ktogdziekiedy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ktogdziekiedy.adapter.RunningTasksAdapter

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val recyclerview = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerview.layoutManager = LinearLayoutManager(this)
        val data = ArrayList<ItemsViewModel>()

        for (i in 1..5) {
            data.add(ItemsViewModel( "Item " + i))
        }



        // This will pass the ArrayList to our Adapter
        val adapter = RunningTasksAdapter(data)

        // Setting the Adapter with the recyclerview
        recyclerview.adapter = adapter
    }
}