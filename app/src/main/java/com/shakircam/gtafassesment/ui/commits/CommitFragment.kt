package com.shakircam.gtafassesment.ui.commits

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewbinding.ViewBinding
import com.facebook.shimmer.ShimmerFrameLayout
import com.shakircam.gtafassesment.data.repository.GithubApiRepositoryImp
import com.shakircam.gtafassesment.databinding.FragmentCommitBinding
import com.shakircam.gtafassesment.databinding.FragmentUserProfileBinding
import com.shakircam.gtafassesment.ui.viewmodel.GithubApiViewModel
import com.shakircam.gtafassesment.ui.viewmodel.GithubApiViewModelFactory
import com.shakircam.gtafassesment.utils.BindingFragment
import com.shakircam.gtafassesment.utils.Resource


class CommitFragment : BindingFragment<FragmentCommitBinding>() {

    private val adapter by lazy { CommitAdapter() }
    private lateinit var shimmerFrameLayout: ShimmerFrameLayout
    private lateinit var githubApiViewModel: GithubApiViewModel


    override val bindingInflater: (LayoutInflater) -> ViewBinding
        get() = FragmentCommitBinding::inflate



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val githubApiRepository = GithubApiRepositoryImp()
        val viewModelProviderFactory = GithubApiViewModelFactory(githubApiRepository)
        githubApiViewModel = ViewModelProvider(this, viewModelProviderFactory).get(GithubApiViewModel::class.java)
        shimmerFrameLayout = binding.shimmerLayout

        initRecyclerView()
        requestApiData()
    }


    private fun requestApiData(){
        githubApiViewModel.commitsResponse.observe(viewLifecycleOwner){ response ->

            when(response){
                is Resource.Success -> {
                    response.data?.let { commitsResponse ->
                        shimmerFrameLayout.isVisible = false
                        adapter.setData(commitsResponse)
                    }
                }

                is Resource.Error -> {

                    response.message?.let { message ->
                        Log.e("tag", "An error occurred: $message")
                    }
                }
                is Resource.Loading -> shimmerFrameLayout.isVisible = true
            }
        }
    }

    override fun onResume() {
        super.onResume()
        shimmerFrameLayout.startShimmer()

    }

    override fun onPause() {
        shimmerFrameLayout.stopShimmer()
        super.onPause()
    }


    private fun initRecyclerView() {
        val mRecyclerView = binding.recyclerView
        mRecyclerView.adapter = adapter
        mRecyclerView.layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL,false)
    }

}