package com.m8.exercicis.bible.recycler_views

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.m8.exercicis.bible.R
import com.m8.exercicis.bible.Verse
import com.m8.exercicis.bible.activities.BottomNavigationActivity.Companion.dbHelper
import com.m8.exercicis.bible.fragments.DetailFragment


/*
 * A TextView variable is passed when creating an instance of this class, to be able to hide or show
 * it from here.
 */
class RecyclerViewAdapter(private var list: MutableList<Verse>, private var lblEmptyList: TextView) :
    RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.item_list, parent, false))
    }

    @SuppressLint("SetTextI18n", "NotifyDataSetChanged")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.txtNom.text = list[position].toString()

        holder.itemView.setOnClickListener { v ->
            val activity = v!!.context as AppCompatActivity
            activity.supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, DetailFragment(list[position]))
                .addToBackStack(null)
                .commit()
        }
    }

    override fun getItemCount() = list.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val txtNom: TextView = view.findViewById(R.id.itemTitol)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun removeAt(position: Int, context: Context) {
        /*
         * When trying to delete a verse from the list, the user is prompt with a dialog to confirm
         * the decision, and after that, a toast is shown with a message according to that choice.
         */
        val builderDeleteVerse = AlertDialog.Builder(context)
        builderDeleteVerse.setMessage(context.getString(R.string.dialog_delete_verse))
            .setPositiveButton(context.getString(R.string.dialog_proceed)) { _, _ ->
                dbHelper.deleteVerse(list[position].id)
                list.removeAt(position)
                notifyDataSetChanged()
                if (list.isEmpty()) lblEmptyList.visibility = View.VISIBLE
                Toast.makeText(
                    context,
                    context.getString(R.string.toast_verse_deleted),
                    Toast.LENGTH_SHORT
                ).show()
            }
            .setNegativeButton(context.getString(R.string.dialog_cancel)) { _, _ ->
                notifyDataSetChanged()
                Toast.makeText(
                    context,
                    context.getString(R.string.toast_cancelled),
                    Toast.LENGTH_SHORT
                ).show()
            }
        builderDeleteVerse.create().show()
    }
}
