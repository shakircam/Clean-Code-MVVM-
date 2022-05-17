package com.shakircam.gtafassesment.data.repository

import com.shakircam.gtafassesment.data.network.ApiService
import com.shakircam.gtafassesment.model.Commits
import com.shakircam.gtafassesment.model.GithubUser
import com.shakircam.gtafassesment.utils.Resource
import retrofit2.Response

class GithubApiRepositoryImp  {
    suspend fun getGithubUserProfile(): Response<GithubUser> {
        return ApiService.api.getGithubUserProfile()
    }

     suspend fun getGithubCommit(): Response<MutableList<Commits.CommitsItem>> {
       return ApiService.api.getGithubCommit()
    }

}