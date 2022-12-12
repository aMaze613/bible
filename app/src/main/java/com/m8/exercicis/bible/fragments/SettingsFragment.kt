package com.m8.exercicis.bible.fragments

import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.Toast
import androidx.preference.Preference
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

    override fun onPreferenceTreeClick(preference: Preference): Boolean {
        return if (preference.key == context?.getString(R.string.delete_settings_key)) {
            activity?.getSharedPreferences("BIBLE_APP_CONFIGURATION", Context.MODE_PRIVATE)
                ?.edit()?.clear()?.apply()
            Toast.makeText(context, getString(R.string.toast_deleted_settings), Toast.LENGTH_SHORT)
                .show()
            true
        } else false
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
