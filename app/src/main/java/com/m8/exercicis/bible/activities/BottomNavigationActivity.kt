package com.m8.exercicis.bible.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.m8.exercicis.bible.R
import com.m8.exercicis.bible.db.VersesDBHelper
import com.m8.exercicis.bible.fragments.FormFragment
import com.m8.exercicis.bible.fragments.HomeFragment
import com.m8.exercicis.bible.fragments.ListFragment


class BottomNavigationActivity : AppCompatActivity() {

    /*
     * A VersesDBHelper variable is declared as a companion object to make it possible to call it's
     * methods from other classes, and the reason to do the same with BottomNavigationView is to be
     * able to hide it from the DetailFragment.
     */
    companion object {
        lateinit var dbHelper: VersesDBHelper
        lateinit var bottomNav: BottomNavigationView
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bottom_navigation)

        /*
         * When assigning the corresponding element to bottomNav, no fragment is loaded by default,
         * so HomeFragment must be loaded manually.
         */
        bottomNav = findViewById(R.id.bottom_navigation)
        loadFragment(HomeFragment())

        dbHelper = VersesDBHelper(this)

        bottomNav.setOnItemSelectedListener { item: MenuItem ->
            when (item.itemId) {
                R.id.nav_home -> {
                    loadFragment(HomeFragment())
                    true
                }
                R.id.nav_form -> {
                    loadFragment(FormFragment())
                    true
                }
                R.id.nav_list -> {
                    loadFragment(ListFragment())
                    true
                }
                else -> false
            }
        }
    }

    private fun loadFragment(fragment: Fragment) = supportFragmentManager.beginTransaction()
        .replace(R.id.fragment_container, fragment)
        .commit()

    override fun onDestroy() {
        super.onDestroy()
        dbHelper.close()
    }

}
