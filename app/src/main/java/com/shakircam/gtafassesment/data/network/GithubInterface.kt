package com.shakircam.gtafassesment.data.network

import com.shakircam.gtafassesment.model.Commits
import retrofit2.Response
import retrofit2.http.GET

interface GithubInterface {

    @GET("repos/flutter/flutter/commits")
    suspend fun getGithubCommit(): Response<MutableList<Commits.CommitsItem>>
}