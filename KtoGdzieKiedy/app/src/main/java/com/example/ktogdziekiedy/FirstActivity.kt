package com.example.ktogdziekiedy

import androidx.appcompat.app.AppCompatActivity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.ahmadhamwi.tabsync.TabbedListMediator
import com.example.ktogdziekiedy.adapter.CategoriesAdapter
import com.example.ktogdziekiedy.model.Category
import com.example.ktogdziekiedy.model.Item
import com.google.android.material.tabs.TabLayout


class FirstActivity: AppCompatActivity() {

        private lateinit var tabLayout: TabLayout
        private lateinit var recyclerView: RecyclerView

        private val categories = mutableListOf(
            Category(
                "Phone",
                Item("LCD"),
                Item("Battery"),
                Item("USB port"),
                Item("Camera"),
                Item("Speaker"),
                Item("Software")
            ),
            Category(
                "Computer",
                Item("CPU"),
                Item("GPU"),
                Item("RAM"),
                Item("PSU"),
                Item("Motherboard"),
                Item("Software"),
                Item("Clean up")
            ),
            Category(
                "Console",
                Item("Controller"),
                Item("Disc"),
                Item("HDMI controller"),
                Item("PSU"),
                Item("Clean up"),
                Item("Software")
            ),
        )

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_first)

            initViews()
            initTabLayout()
            initRecycler()
            initMediator()

        }

        private fun initViews() {
            tabLayout = findViewById(R.id.tabLayout)
            recyclerView = findViewById(R.id.recyclerView)
        }

        private fun initTabLayout() {
            for (category in categories) {
                tabLayout.addTab(tabLayout.newTab().setText(category.name))
            }
        }

        private fun initRecycler() {
            recyclerView.adapter = CategoriesAdapter(this, categories)
        }

        private fun initMediator() {
            TabbedListMediator(
                recyclerView,
                tabLayout,
                categories.indices.toList()
            ).attach()
        }


    }