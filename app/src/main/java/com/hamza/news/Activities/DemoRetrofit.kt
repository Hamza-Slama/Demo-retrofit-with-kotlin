package com.hamza.news.Activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.hamza.news.Adapter.GitHubRepoAdapter
import com.hamza.news.Models.Repo
import com.hamza.news.R

//import com.hamza.news.R.id.tvShowRepo
import com.hamza.news.Services.GitHubService
import kotlinx.android.synthetic.main.activity_demo_retrofit.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.adapter.rxjava2.Result.response
import retrofit2.adapter.rxjava2.Result.response








class DemoRetrofit : AppCompatActivity() {
    var adapter : GitHubRepoAdapter?=null
    //TODO : Second methode
    private val githubservices by lazy {
        GitHubService.create()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_demo_retrofit)

        //TODO : 1er methode
        val builder = Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())

        val retrofit = builder.build()
        val client  = retrofit.create(GitHubService::class.java!!)
        val call = client.listRepos("eddydn")
        val call2 = githubservices.listRepos("eddydn")


        tvShowRepo.text=call.toString()
//        tc.text = call.toString()
        btnShowRepo.setOnClickListener {
        call.enqueue(object : Callback<List<Repo>>{
            override fun onResponse(call: Call<List<Repo>>?, response: Response<List<Repo>>?) {
                val repos = response!!.body()!!
                val s = repos.size
                tvShowRepo.text="size = $s"
                adapter = GitHubRepoAdapter(applicationContext, repos)
                lsViewRepo.adapter=adapter

            }

            override fun onFailure(call: Call<List<Repo>>?, t: Throwable?) {

            }

        })
        }

    }
}
