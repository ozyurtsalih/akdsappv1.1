package com.example.akdsapp.Tests

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.akdsapp.Message.NewRoomFragment
import com.example.akdsapp.R
import com.example.akdsapp.utils.BottomnavigationViewHelper
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.bottomNavigationView
import kotlinx.android.synthetic.main.activity_message.*
import kotlinx.android.synthetic.main.activity_tests.*

class TestsActivity : AppCompatActivity() {
    private val ACTİVİTY_NO = 1
    private val TAG = "HomeActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tests)
        setupNavigationView()
        cw1.setOnClickListener {
            var dialog= KaracigerFragment()
            dialog.show(supportFragmentManager,"goster")
        }
        CW2.setOnClickListener {
            var dialog= KansizlikFragment()
            dialog.show(supportFragmentManager,"goster")
        }

        CW3.setOnClickListener {
            var dialog= VitaminFragment()
            dialog.show(supportFragmentManager,"goster")
        }

        CW4.setOnClickListener {
            var dialog= KolestrolFragment()
            dialog.show(supportFragmentManager,"goster")
        }


    }

    fun setupNavigationView() {
        BottomnavigationViewHelper.setupBottomNavigationView(bottomNavigationView)
        BottomnavigationViewHelper.setupNavigation(this, bottomNavigationView)
        var menu = bottomNavigationView.menu
        var menuItem = menu.getItem(ACTİVİTY_NO)
        menuItem.setChecked(true)
    }
}