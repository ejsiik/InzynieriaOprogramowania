package com.example.ktogdziekiedy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ktogdziekiedy.adapter.ItemsAdapter
import com.example.ktogdziekiedy.adapter.RunningTasksAdapter

class SecondActivity : AppCompatActivity() {
    //TODO: create dynamic list of running tasks based on onClick method in first activity
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        // getting the recyclerview by its id
        val recyclerview = findViewById<RecyclerView>(R.id.recyclerView)

        // this creates a vertical layout Manager
        recyclerview.layoutManager = LinearLayoutManager(this)

        // ArrayList of class ItemsViewModel
        val data = ArrayList<ItemsViewModel>()

        // This loop will create 20 Views containing
        // the image with the count of view
        // TODO: set user image
        for (i in 1..5) {
            data.add(ItemsViewModel(R.drawable.a, "Item " + i))
        }

        // This will pass the ArrayList to our Adapter
        val adapter = RunningTasksAdapter(data)

        // Setting the Adapter with the recyclerview
        recyclerview.adapter = adapter
    }
}