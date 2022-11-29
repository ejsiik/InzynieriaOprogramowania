package com.example.ktogdziekiedy
import android.content.res.Resources
import android.os.Parcel
import android.os.Parcelable
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.ktogdziekiedy.fragments.FirstFragment
import com.example.ktogdziekiedy.fragments.SecondFragment
import com.example.ktogdziekiedy.fragments.ThirdFragment


class MyAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount() = 3

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> {
                return FirstFragment()
            }
            1 -> {
                return SecondFragment()
            }
            2 -> {
                return ThirdFragment()
            }
            else -> {
                throw Resources.NotFoundException("Nie znaleziono pozycji")
            }
        }
    }

}