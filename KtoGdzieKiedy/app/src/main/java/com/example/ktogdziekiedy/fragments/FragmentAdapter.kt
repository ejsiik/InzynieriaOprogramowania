package com.example.ktogdziekiedy.fragments

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class FragmentAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle): FragmentStateAdapter(fragmentManager, lifecycle){
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> {
                PhoneFragment()
            }
            1 -> {
                ComputerFragment()
            }
            2 -> {
                ConsoleFragment()
            }
            else -> {
                Fragment()
            }
        }
    }
}