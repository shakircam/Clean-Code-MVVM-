package com.shakircam.gtafassesment.data.network

import com.shakircam.gtafassesment.model.Commits
import com.shakircam.gtafassesment.model.GithubUser
import retrofit2.Response
import retrofit2.http.GET

interface GithubApi {

    @GET("repos/flutter/flutter/commits")
    suspend fun getGithubCommit(): Response<MutableList<Commits.CommitsItem>>

    @GET("users/hasancse91")
    suspend fun getGithubUserProfile(): Response<GithubUser>
}