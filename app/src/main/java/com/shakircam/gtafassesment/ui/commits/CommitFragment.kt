package com.shakircam.gtafassesment.ui.commits

import android.app.Application
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.shakircam.gtafassesment.data.repository.GithubApiRepositoryImp
import com.shakircam.gtafassesment.databinding.FragmentCommitBinding
import com.shakircam.gtafassesment.ui.viewmodel.GithubApiViewModel
import com.shakircam.gtafassesment.ui.viewmodel.GithubApiViewModelFactory
import com.shakircam.gtafassesment.utils.Resource


class CommitFragment : Fragment() {
    private var _binding: FragmentCommitBinding? = null
    private val binding get() = _binding!!

    private lateinit var githubApiViewModel: GithubApiViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentCommitBinding.inflate(inflater, container, false)
        val githubApiRepository = GithubApiRepositoryImp()
        val viewModelProviderFactory = GithubApiViewModelFactory(githubApiRepository,application = Application())
        githubApiViewModel = ViewModelProvider(this, viewModelProviderFactory).get(GithubApiViewModel::class.java)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        githubApiViewModel.commitsResponse.observe(viewLifecycleOwner){ response ->

            when(response){
                is Resource.Success -> {
                    response.data?.let { commitsResponse ->
                        Log.d("tag","commits::${commitsResponse[1].sha}")
                    }
                }
                is Resource.Error -> {

                    response.message?.let { message ->
                        Log.e("tag", "An error occurred: $message")
                    }
                }
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}