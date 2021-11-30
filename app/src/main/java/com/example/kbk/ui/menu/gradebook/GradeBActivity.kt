package com.example.kbk.ui.menu.gradebook

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.viewpager2.widget.ViewPager2
import com.example.kbk.R
import com.example.kbk.ui.dashboard.VPDashbAdapter
import kotlinx.android.synthetic.main.fragment_dashboard.view.*

class GradeBActivity : AppCompatActivity() {
    private lateinit var viewPager: ViewPager2
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.menu_gradeb)

        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)
        getSupportActionBar()?.setDisplayShowHomeEnabled(true)
        toolbar.setNavigationOnClickListener {
            onBackPressed() // возврат на предыдущий activity
        }

        viewPager = findViewById(R.id.viewpager_gradeb)
        viewPager.adapter = VPGradeBAdapter(this)


    }
}