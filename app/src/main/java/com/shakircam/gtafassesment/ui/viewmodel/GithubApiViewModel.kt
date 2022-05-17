package com.shakircam.gtafassesment.ui.viewmodel

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shakircam.gtafassesment.data.repository.GithubApiRepository
import com.shakircam.gtafassesment.data.repository.GithubApiRepositoryImp
import com.shakircam.gtafassesment.model.Commits
import com.shakircam.gtafassesment.model.GithubUser
import com.shakircam.gtafassesment.utils.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class GithubApiViewModel(private val repository : GithubApiRepositoryImp) : ViewModel() {


    /** RETROFIT */
     var commitsResponse: MutableLiveData<Resource<MutableList<Commits.CommitsItem>>> = MutableLiveData()
     var userResponse: MutableLiveData<Resource<GithubUser>> = MutableLiveData()


    init {
        getCommit()
    }

    private fun getCommit() = viewModelScope.launch {
        commitsResponse.postValue(Resource.Loading())
        val response = repository.getGithubCommit()
        commitsResponse.postValue(handleCommitsResponse(response))
    }


    private fun handleCommitsResponse(response: Response<MutableList<Commits.CommitsItem>>) : Resource<MutableList<Commits.CommitsItem>> {
        if(response.isSuccessful) {
            response.body()?.let { resultResponse ->

                val filterList = mutableListOf<Commits.CommitsItem>()
                val filterList1 = mutableListOf<Commits.CommitsItem>()

                /** we are checking user name starts with g & x .If match with condition then we are keeping those a list.After that removing from main list.
                   And finally keeping only last 10 commits   */


                for (element in resultResponse){
                    if (element.commit.author.name.startsWith("g") || element.commit.author.name.startsWith("x")){
                        filterList.add(element)
                    }
                }

                resultResponse.removeAll(filterList)
                var commitItem = 0
                for (i in resultResponse){

                    if (commitItem<10){
                        filterList1.add(i)
                        commitItem++
                    }
                }
                return Resource.Success(filterList1)
            }

        }
        return Resource.Error(response.message())
    }

    init {
        getUserProfile()
    }

    private fun getUserProfile() = viewModelScope.launch {
        userResponse.postValue(Resource.Loading())
        val response = repository.getGithubUserProfile()
        userResponse.postValue(handleUserProfileResponse(response))
    }

    private fun handleUserProfileResponse(response: Response<GithubUser>) : Resource<GithubUser> {
        if(response.isSuccessful) {
            response.body()?.let { resultResponse ->

                return Resource.Success(resultResponse)
            }

        }
        return Resource.Error(response.message())
    }

}