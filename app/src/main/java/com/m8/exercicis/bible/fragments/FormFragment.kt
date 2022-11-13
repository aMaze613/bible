package com.m8.exercicis.bible.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.m8.exercicis.bible.R
import com.m8.exercicis.bible.Verse
import com.m8.exercicis.bible.activities.BottomNavigationActivity.Companion.dbHelper

class FormFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val view: View = inflater.inflate(R.layout.fragment_form, container, false)

        val txtBook: EditText = view.findViewById(R.id.txtBook)
        val txtChapter: EditText = view.findViewById(R.id.txtChapter)
        val txtVerse: EditText = view.findViewById(R.id.txtVerse)
        val txtText: EditText = view.findViewById(R.id.txtText)
        val lblCreateResult: TextView = view.findViewById(R.id.lblCreateResult)

        val btnCreate: Button = view.findViewById(R.id.btnCreate)
        val btnDeleteAll: Button = view.findViewById(R.id.btnDeleteAll)

        btnCreate.setOnClickListener {
            if (txtBook.text.toString().isNotBlank()
                && txtChapter.text.toString().isNotBlank()
                && txtVerse.text.toString().isNotBlank()
                && txtText.text.toString().isNotBlank()
            ) {
                dbHelper.insertVerse(
                    Verse(
                        txtBook.text.toString(),
                        txtChapter.text.toString().toInt(),
                        txtVerse.text.toString().toInt(),
                        txtText.text.toString()
                    )
                )
                Log.i("AddedVerse", getString(R.string.added_verse))
                lblCreateResult.text = getString(R.string.added_verse)
            } else {
                Log.i("NotAddedVerse", getString(R.string.not_added_verse))
                lblCreateResult.text = getString(R.string.not_added_verse)
            }
        }

        btnDeleteAll.setOnClickListener {
            dbHelper.deleteAllVerses()
            Log.i("DeletedAll", getString(R.string.deleted_all))
            lblCreateResult.text = getString(R.string.deleted_all)
        }

        return view
    }

}