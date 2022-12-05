package com.m8.exercicis.bible.fragments

import android.os.Bundle
import android.view.Menu
import android.view.View
import androidx.preference.PreferenceFragmentCompat
import com.m8.exercicis.bible.R
import com.m8.exercicis.bible.activities.BottomNavigationActivity.Companion.bottomNav


class SettingsFragment : PreferenceFragmentCompat() {

    override fun onPrepareOptionsMenu(menu: Menu) {
        menu.clear()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        /*
         * When a SettingsFragment instance is created, the bottom navigation is hidden, since with
         * the back button from the phone is enough to return to the previous fragment.
         */
        bottomNav.visibility = View.GONE
    }

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)
    }

    override fun onDestroy() {
        super.onDestroy()
        /*
         * When the SettingsFragment instance gets destroyed, probably because the user has pressed
         * the back button, the bottom navigation gets visible again.
         */
        bottomNav.visibility = View.VISIBLE
    }

}
