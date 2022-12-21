package com.m8.exercicis.bible.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.m8.exercicis.bible.R
import com.m8.exercicis.bible.db.VersesDBHelper
import com.m8.exercicis.bible.fragments.FormFragment
import com.m8.exercicis.bible.fragments.HomeFragment
import com.m8.exercicis.bible.fragments.ListFragment
import com.m8.exercicis.bible.fragments.SettingsFragment

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
        loadFragment(HomeFragment(), false)

        dbHelper = VersesDBHelper(this)

        bottomNav.setOnItemSelectedListener { item: MenuItem ->
            when (item.itemId) {
                R.id.nav_home -> {
                    loadFragment(HomeFragment(), false)
                    true
                }
                R.id.nav_form -> {
                    loadFragment(FormFragment(), false)
                    true
                }
                R.id.nav_list -> {
                    loadFragment(ListFragment(), false)
                    true
                }
                else -> false
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.settings, menu)
        return true
    }

    /*
     * On the settings menu at the top right corner, there are two items. If the settings one is
     * selected, the SettingsFragment is shown, and if the logout one is selected, app returns to
     * Login Activity and removes the Shared Preference to login automatically.
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        R.id.item_settings -> {
            loadFragment(SettingsFragment(), true)
            true
        }
        R.id.item_logout -> {
            getSharedPreferences("BIBLE_APP_CONFIGURATION", Context.MODE_PRIVATE)
                .edit().putBoolean("logged", false).apply()
            val intent = Intent(this, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            true
        }
        else -> super.onOptionsItemSelected(item)
    }

    private fun loadFragment(fragment: Fragment, addToBackStack: Boolean) {
        val transactionManager = supportFragmentManager.beginTransaction()
        transactionManager.replace(R.id.fragment_container, fragment)
        if (addToBackStack) transactionManager.addToBackStack(null)
        transactionManager.commit()
    }

    override fun onDestroy() {
        super.onDestroy()
        dbHelper.close()
    }

}
