package com.shakircam.gtafassesment.ui.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.shakircam.gtafassesment.data.repository.GithubApiRepository
import com.shakircam.gtafassesment.data.repository.GithubApiRepositoryImp

class GithubApiViewModelFactory(private val githubApiRepository: GithubApiRepositoryImp, private val application: Application) : ViewModelProvider.Factory  {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return GithubApiViewModel(githubApiRepository,application) as T
    }
}