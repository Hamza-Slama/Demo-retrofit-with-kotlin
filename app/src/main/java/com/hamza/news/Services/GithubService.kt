package com.hamza.news.Services

import com.hamza.news.Constant.GITHUB_URL
import com.hamza.news.Models.Repo
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by hamza on 30/01/18.
 */

interface GitHubService {
    @GET("users/{user}/repos")
    fun listRepos(@Path("user") user: String): Call<List<Repo>>

    companion object {
        fun create(): GitHubService {

            val retrofit = Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(GITHUB_URL)
                    .build()

            return retrofit.create(GitHubService::class.java)
        }
    }
}