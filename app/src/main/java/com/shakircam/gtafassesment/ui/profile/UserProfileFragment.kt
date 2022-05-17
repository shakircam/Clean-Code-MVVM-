package com.shakircam.gtafassesment.ui.profile

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import coil.load
import com.shakircam.gtafassesment.R
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

        githubApiViewModel.userResponse.observe(viewLifecycleOwner){ user->
            val user = user.data
            Log.d("tag","image::${user?.avatarUrl}")
            binding.user = GithubUser(user?.avatarUrl,user?.name,user?.location,user?.bio,
                user?.publicRepos,user?.publicGists)

            /*
            binding.imageView.load("https://avatars.githubusercontent.com/u/47291818?v=4"){
                crossfade(600)
            }
            binding.nameTv.text = user.data?.name
            binding.emailTv.text = user.data?.name
            binding.bioTv.text = user.data?.bio
            binding.emailTv.text = user.data?.location
            binding.publicRepoTv.text = user.data?.publicRepos.toString()
            binding.publicGistTv.text = user.data?.publicGists.toString()*/
        }
    }
}