package com.shakircam.gtafassesment.ui.profile

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.shakircam.gtafassesment.data.repository.GithubApiRepositoryImp
import com.shakircam.gtafassesment.databinding.FragmentUserProfileBinding
import com.shakircam.gtafassesment.model.GithubUser
import com.shakircam.gtafassesment.ui.viewmodel.GithubApiViewModel
import com.shakircam.gtafassesment.ui.viewmodel.GithubApiViewModelFactory
import com.shakircam.gtafassesment.utils.BindingFragment


class UserProfileFragment : BindingFragment<FragmentUserProfileBinding>() {

    private lateinit var githubApiViewModel: GithubApiViewModel

    override val bindingInflater: (LayoutInflater) -> ViewBinding
        get() = FragmentUserProfileBinding::inflate


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val githubApiRepository = GithubApiRepositoryImp()
        val viewModelProviderFactory = GithubApiViewModelFactory(githubApiRepository)
        githubApiViewModel = ViewModelProvider(this, viewModelProviderFactory).get(GithubApiViewModel::class.java)

        githubApiViewModel.userResponse.observe(viewLifecycleOwner){ result->
            val user = result.data
            binding.user = GithubUser(
                user?.avatar_url,user?.name,
                user?.location,user?.bio,
                user?.public_repos,user?.public_gists
            )
            Log.d("tag","image::${user?.avatar_url}")
            Log.d("tag","bio::${user?.bio}")
        }
    }
}