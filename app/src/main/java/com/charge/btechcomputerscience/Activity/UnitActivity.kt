package com.charge.btechcomputerscience.Activity


import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.viewpager.widget.ViewPager
import com.charge.btechcomputerscience.Adapter.PageAdapter
import com.charge.btechcomputerscience.R
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.google.android.material.tabs.TabLayout.TabLayoutOnPageChangeListener

class UnitActivity : AppCompatActivity() {
    private lateinit var viewPager: ViewPager
    private lateinit var tabLayout: TabLayout
    private lateinit var sem:String
    private lateinit var subject:String
    private lateinit var unit:String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_unit)
//        initialize
        viewPager = findViewById(R.id.vpUnitViewPager)
        tabLayout = findViewById(R.id.tabLayout)

        sem = intent.getStringExtra("sem").toString()
        subject = intent.getStringExtra("subject").toString()
        unit = intent.getStringExtra("unit").toString()

        saveData()
//        set viewpager and tablayout
        tabLayout.addTab(tabLayout.newTab().setText("Notes"))
        tabLayout.addTab(tabLayout.newTab().setText("Videos"))
        tabLayout.addTab(tabLayout.newTab().setText("Other"))
        tabLayout.tabGravity = TabLayout.GRAVITY_FILL

        val adapter = PageAdapter(this,supportFragmentManager,tabLayout.tabCount)
        try {
            viewPager.adapter = adapter
        }
        catch(e: Exception){
            Log.d("error in ",e.message.toString())
        }
        viewPager.addOnPageChangeListener(TabLayoutOnPageChangeListener(tabLayout))
        tabLayout.addOnTabSelectedListener(object: OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab != null) {
                    viewPager.currentItem = tab.position
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}

            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })

    }

    private fun saveData() {
        val sharedPreferences = getSharedPreferences("sharedPrefs",Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.apply{
            putString("sem",sem)
            putString("subject",subject)
            putString("unit",unit)
        }.apply()
        Log.d("save data","save data")
    }


}