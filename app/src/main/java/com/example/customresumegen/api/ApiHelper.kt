package com.example.customresumegen.api

import android.provider.ContactsContract.CommonDataKinds.Callable
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


fun getDetails(resume: (List<Resume>) -> Unit){
resumeCallable.getResume().enqueue(object :Callback<Results>{
    override fun onResponse(call: Call<Results>, response: Response<Results>) {
        val data =response.body()
        val results =data?.results!!
        resume(results)
    }

    override fun onFailure(call: Call<Results>, t: Throwable) {

    }

})

}