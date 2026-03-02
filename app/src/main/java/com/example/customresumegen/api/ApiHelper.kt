package com.example.customresumegen.api

import android.provider.ContactsContract.CommonDataKinds.Callable
import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private val resumeRetrofit = Retrofit.Builder()
    .baseUrl("https://expressjs-api-resume-random.onrender.com/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()
private val resumeCallable = resumeRetrofit.create(ResumeCallable::class.java)


fun getDetails(resume: (Resume) -> Unit){
resumeCallable.getResume().enqueue(object :Callback<Resume>{
    override fun onResponse(call: Call<Resume>, response: Response<Resume>) {
            val data = response.body()
            resume(data!!)
        }

    override fun onFailure(call: Call<Resume>, t: Throwable) {
        Log.d("error","there is a failure")
    }

})

}