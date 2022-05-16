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
import androidx.lifecycle.viewModelScope
import com.shakircam.gtafassesment.data.repository.GithubApiRepository
import com.shakircam.gtafassesment.data.repository.GithubApiRepositoryImp
import com.shakircam.gtafassesment.model.Commits
import com.shakircam.gtafassesment.utils.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class GithubApiViewModel(private val repository : GithubApiRepositoryImp, application: Application) : AndroidViewModel(application) {


    /** RETROFIT */
     var commitsResponse: MutableLiveData<Resource<MutableList<Commits.CommitsItem>>> = MutableLiveData()


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

              val list = mutableListOf<Commits.CommitsItem>()

                Log.d("tag","list size before delete::${list.size}")
                for (element in resultResponse){
                    if (element.commit.author.name.startsWith("e") || element.commit.author.name.startsWith("x")){
                        list.add(element)
                    }
                }

                resultResponse.removeAll(list)
                Log.d("tag","list size after delete::${list.size}")
                return Resource.Success(resultResponse)
            }

        }
        return Resource.Error(response.message())
    }



   /* init {
        getCommits()
    }

       private fun getCommits() = viewModelScope.launch {
        getCommitsSafeCall()
      }
   private suspend  fun getCommitsSafeCall() {
       commitsResponse.value = Resource.Loading()
       if (hasInternetConnection()){
           try {
               val response = repository.getGithubCommit()
               commitsResponse.value = handleCommitResponse(response)

           }catch (e: Exception){
               commitsResponse.value = Resource.Error("Commits not found.")
           }
       }else{
           commitsResponse.value = Resource.Error("No Internet Connection.")
       }
    }

    private fun handleCommitResponse(response: Response<MutableList<Commits.CommitsItem>>): Resource<MutableList<Commits.CommitsItem>>? {

        when {
            response.message().toString().contains("timeout") -> {
                return Resource.Error("Timeout")
            }
            response.code() == 401 -> {
                return Resource.Error("Resource not found.")
            }

            response.isSuccessful -> {
                val commits = response.body()
                return Resource.Success(commits!!)
            }
            else -> {
                return Resource.Error(response.message())
            }
        }
    }


    private fun hasInternetConnection(): Boolean {
        val connectivityManager = getApplication<Application>().getSystemService(
            Context.CONNECTIVITY_SERVICE
        ) as ConnectivityManager
        val activeNetwork = connectivityManager.activeNetwork ?: return false
        val capabilities = connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false
        return when {
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            else -> false
        }
    }*/
}