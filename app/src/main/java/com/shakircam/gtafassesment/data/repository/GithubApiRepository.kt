package com.shakircam.gtafassesment.data.repository

import com.shakircam.gtafassesment.model.Commits
import com.shakircam.gtafassesment.model.GithubUser
import com.shakircam.gtafassesment.utils.Resource
import retrofit2.Response

interface GithubApiRepository {

    suspend fun getGithubUserProfile(): Response<GithubUser>
    suspend fun getGithubCommit(): Response<MutableList<Commits.CommitsItem>>

}