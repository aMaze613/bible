package com.m8.exercicis.bible.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.m8.exercicis.bible.R
import com.m8.exercicis.bible.SwipeToDeleteCallback
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
        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerList)
        recyclerView.layoutManager = LinearLayoutManager(context)
        val adapter = RecyclerViewAdapter(list, lblEmptyList)
        recyclerView.adapter = adapter
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                context,
                DividerItemDecoration.VERTICAL
            )
        )

        val swipeHandler = object : SwipeToDeleteCallback(requireContext()) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val recyclerViewAdapter = recyclerView.adapter as RecyclerViewAdapter
                recyclerViewAdapter.removeAt(viewHolder.adapterPosition, context!!)
            }
        }
        val itemTouchHelper = ItemTouchHelper(swipeHandler)
        itemTouchHelper.attachToRecyclerView(recyclerView)

        /*
         * If the list from the recycler view is empty, the element is hidden to let a message be
         * visible indicating that the list is indeed empty.
         */
        if (list.isEmpty()) recyclerView.visibility = View.GONE
        else lblEmptyList.visibility = View.GONE

        return view
    }

}
