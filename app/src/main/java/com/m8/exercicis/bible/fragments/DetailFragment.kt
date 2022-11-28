package com.m8.exercicis.bible.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.m8.exercicis.bible.R
import com.m8.exercicis.bible.Verse
import com.m8.exercicis.bible.activities.BottomNavigationActivity.Companion.bottomNav


/*
 * A Verse variable is passed when creating an instance of this class, to be able to access the
 * content of the clicked verse from the list.
 */
class DetailFragment(private val verse: Verse) : Fragment() {

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view: View = inflater.inflate(R.layout.fragment_detail, container, false)

        val lblVerseTitle: TextView = view.findViewById(R.id.lblVerseTitle)
        val lblVerseText: TextView = view.findViewById(R.id.lblVerseText)
        lblVerseTitle.text = verse.toString()
        lblVerseText.text = "\"${verse.text}\""

        /*
         * When a DetailFragment instance is created, the bottom navigation is hidden, since with
         * the back button from the phone is enough to return to the previous fragment.
         */
        bottomNav.visibility = View.GONE

        return view
    }

    override fun onDestroy() {
        super.onDestroy()
        /*
         * When the DetailFragment instance gets destroyed, probably because the user has pressed
         * the back button, the bottom navigation gets visible again.
         */
        bottomNav.visibility = View.VISIBLE
    }

}
