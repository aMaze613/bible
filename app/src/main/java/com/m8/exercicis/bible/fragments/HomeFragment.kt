package com.m8.exercicis.bible.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.m8.exercicis.bible.ApiCall
import com.m8.exercicis.bible.R
import com.m8.exercicis.bible.Verse
import com.m8.exercicis.bible.models.ModelApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        val txtBookHome: EditText = view.findViewById(R.id.txtBookHome)
        val txtChapterHome: EditText = view.findViewById(R.id.txtChapterHome)
        val txtVerseHome: EditText = view.findViewById(R.id.txtVerseHome)
        val btnSearch: Button = view.findViewById(R.id.btnSearch)

        /*
         * To search for a verse in the API, all fields must be filled, and if not, a toast is shown
         * warning the user.
         */
        btnSearch.setOnClickListener {
            if (txtBookHome.text.toString().isNotBlank()
                && txtChapterHome.text.toString().isNotBlank()
                && txtVerseHome.text.toString().isNotBlank()
            ) {
                val retrofit = Retrofit.Builder()
                    .baseUrl("https://bible-api.com")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                val apiCall = retrofit.create(ApiCall::class.java)
                val call: Call<ModelApi?>? = apiCall.getData(
                    "${txtBookHome.text}+${txtChapterHome.text}:${txtVerseHome.text}".replace(
                        " ",
                        ""
                    ).lowercase()
                )
                call?.enqueue(object : Callback<ModelApi?> {
                    override fun onResponse(call: Call<ModelApi?>, response: Response<ModelApi?>) {
                        if (response.code() != 200) {
                            Toast.makeText(
                                context,
                                getString(R.string.verse_not_found_toast),
                                Toast.LENGTH_SHORT
                            )
                                .show()
                            return
                        }
                        activity?.supportFragmentManager?.beginTransaction()
                            ?.replace(
                                R.id.fragment_container, DetailFragment(
                                    Verse(
                                        -1,
                                        txtBookHome.text.toString(),
                                        txtChapterHome.text.toString().toInt(),
                                        txtVerseHome.text.toString().toInt(),
                                        response.body()?.verses?.get(0)?.text?.trim() ?: ""
                                    )
                                )
                            )
                            ?.addToBackStack(null)
                            ?.commit()
                    }

                    override fun onFailure(call: Call<ModelApi?>, t: Throwable) {
                        Toast.makeText(
                            context,
                            getString(R.string.error_occurred_toast),
                            Toast.LENGTH_SHORT
                        )
                            .show()
                    }

                })
            } else Toast.makeText(
                context,
                getString(R.string.toast_fill_fields),
                Toast.LENGTH_SHORT
            ).show()
        }

        return view
    }

}