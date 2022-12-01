package com.m8.exercicis.bible.fragments

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import com.m8.exercicis.bible.R


class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)
    }

}
