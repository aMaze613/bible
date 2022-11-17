package com.m8.exercicis.bible.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.m8.exercicis.bible.R
import com.m8.exercicis.bible.Verse
import com.m8.exercicis.bible.activities.BottomNavigationActivity.Companion.dbHelper
import com.m8.exercicis.bible.recycler_views.RecyclerViewAdapter

class ListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view: View = inflater.inflate(R.layout.fragment_list, container, false)

        val lblEmptyList: TextView = view.findViewById(R.id.lblEmptyList)
        val list: MutableList<Verse> = dbHelper.getVerses()

        Log.i("ListVerses", list.toString())

        if (list.isNotEmpty()) {
            lblEmptyList.visibility = View.GONE
            val recyclerView: RecyclerView = view.findViewById(R.id.recyclerList)
            recyclerView.layoutManager = LinearLayoutManager(context)
            val adapter = RecyclerViewAdapter(list)
            recyclerView.adapter = adapter
            recyclerView.addItemDecoration(
                DividerItemDecoration(
                    context,
                    DividerItemDecoration.VERTICAL
                )
            )
        }

        return view
    }

}