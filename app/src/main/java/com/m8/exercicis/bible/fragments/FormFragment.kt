package com.m8.exercicis.bible.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
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
        val btnCreate: Button = view.findViewById(R.id.btnCreate)
        val btnDeleteAll: Button = view.findViewById(R.id.btnDeleteAll)

        /*
         * When trying to delete all verses from the list, the user is prompt with a dialog to
         * confirm the decision, and after that, a toast is shown with a message according to that
         * choice.
         */
        val builderDeleteList = AlertDialog.Builder(context)
        builderDeleteList.setMessage(getString(R.string.dialog_delete_data))
            .setPositiveButton(getString(R.string.dialog_proceed)) { _, _ ->
                dbHelper.deleteAllVerses()
                Toast.makeText(context, getString(R.string.toast_data_deleted), Toast.LENGTH_SHORT)
                    .show()
            }
            .setNegativeButton(getString(R.string.dialog_cancel)) { _, _ ->
                Toast.makeText(context, getString(R.string.toast_cancelled), Toast.LENGTH_SHORT)
                    .show()
            }

        /*
         * To create a new verse, all fields must be filled, and if not, a toast is shown warning
         * the user.
         */
        btnCreate.setOnClickListener {
            if (txtBook.text.toString().isNotBlank()
                && txtChapter.text.toString().isNotBlank()
                && txtVerse.text.toString().isNotBlank()
                && txtText.text.toString().isNotBlank()
            ) {
                dbHelper.insertVerse(
                    Verse(
                        -1,
                        txtBook.text.toString(),
                        txtChapter.text.toString().toInt(),
                        txtVerse.text.toString().toInt(),
                        txtText.text.toString()
                    )
                )
                txtBook.text.clear()
                txtChapter.text.clear()
                txtVerse.text.clear()
                txtText.text.clear()
                Toast.makeText(context, getString(R.string.toast_new_verse), Toast.LENGTH_SHORT).show()
            } else Toast.makeText(context, getString(R.string.toast_fill_fields), Toast.LENGTH_SHORT).show()
        }

        btnDeleteAll.setOnClickListener {
            builderDeleteList.create().show()
        }

        return view
    }

}
