package com.example.customresumegen.api

import retrofit2.Call
import retrofit2.http.GET

interface ResumeCallable {
    @GET("resume?name=insert-your-name-here")
    fun getResume():Call<Results>
}