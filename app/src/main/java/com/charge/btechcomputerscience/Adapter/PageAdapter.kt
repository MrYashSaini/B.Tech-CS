package com.charge.btechcomputerscience.Adapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.charge.btechcomputerscience.Fragment.OtherFragment
import com.charge.btechcomputerscience.Fragment.PdfFragment
import com.charge.btechcomputerscience.Fragment.VideoFragment

@Suppress("DEPRECATION")
internal class PageAdapter(
    var context: Context,
    fm: FragmentManager,
    var totalTabs: Int
) :
    FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                PdfFragment()
            }
            1 -> {
                VideoFragment()
            }
            2 -> {
                OtherFragment()
            }
            else -> getItem(position)
        }
    }
    override fun getCount(): Int {
        return totalTabs
    }

}