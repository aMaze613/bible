package com.m8.exercicis.bible

import com.m8.exercicis.bible.models.ModelApi
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiCall {
    @GET("/{verse}")
    fun getData(@Path("verse") verse: String?): Call<ModelApi?>?
}