package com.example.ktogdziekiedy

import androidx.appcompat.app.AppCompatActivity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
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
                Item("Item 1"),
                Item("Item 2"),
                Item("Item 3"),
                Item("Item 4"),
                Item("Item 5"),
                Item("Item 6")
            ),
            Category(
                "Computer",
                Item("Item 1"),
                Item("Item 2"),
                Item("Item 3"),
                Item("Item 4"),
            ),
            Category(
                "Console",
                Item("Item 1"),
                Item("Item 2"),
                Item("Item 3"),
                Item("Item 4"),
                Item("Item 5"),
                Item("Item 6"),
                Item("Item 7"),
                Item("Item 8"),
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