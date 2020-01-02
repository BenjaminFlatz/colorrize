package com.example.e4

import android.app.Activity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.e4.ui.Fragment_1
import com.example.e4.ui.Fragment_2
import com.example.e4.ui.Fragment_3

class PagerAdapter(
    fm: Activity,
    supportFragmentManager: FragmentManager,
    tabCount: Int
) : FragmentPagerAdapter(supportFragmentManager) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                //  val homeFragment: HomeFragment = HomeFragment()
                Fragment_1()
            }
            1 -> {
                Fragment_2()
            }
            2 -> {
                // val movieFragment = MovieFragment()
                Fragment_3()
            }
            else -> Fragment_1()
        }
    }

    override fun getCount(): Int {
        return 3
    }

    override fun getPageTitle(position: Int): CharSequence {
        return when (position) {
            0 -> "RGB"
            1 -> "Tunable White"
            else -> {
                return "Animations"
            }
        }
    }
}