package com.example.ktogdziekiedy

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.example.ktogdziekiedy.fragments.FragmentAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class FirstActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_first)
        val tabLayout = findViewById<TabLayout>(R.id.tabl)
        val viewPager2 = findViewById<ViewPager2>(R.id.vpFirst)

        val adapter = FragmentAdapter(supportFragmentManager, lifecycle)

        viewPager2.adapter = adapter

        TabLayoutMediator(tabLayout, viewPager2) {
            tab, position ->
            when (position) {
                0 -> {
                    tab.text = "Phone"
                }
                1 -> {
                    tab.text = "Computer"
                }
                2 -> {
                    tab.text = "Console"
                }
            }
        }.attach()

    }
}