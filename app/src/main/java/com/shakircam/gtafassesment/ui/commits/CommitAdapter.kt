package com.shakircam.gtafassesment.ui.commits

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.shakircam.gtafassesment.databinding.RecyclerItemRowBinding
import com.shakircam.gtafassesment.model.Commits

class CommitAdapter : RecyclerView.Adapter<CommitAdapter.CommitViewHolder>() {

    private var receiveCommit = emptyList<Commits.CommitsItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommitViewHolder {
        return CommitViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: CommitViewHolder, position: Int) {
        val currentCommit = receiveCommit[position]
        holder.bind(currentCommit)
    }

    override fun getItemCount(): Int {
        return receiveCommit.size
    }

    class CommitViewHolder(private val binding: RecyclerItemRowBinding)  : RecyclerView.ViewHolder(binding.root) {

        fun bind(commit: Commits.CommitsItem){
            binding.commit = commit
            binding.executePendingBindings()

        }

        companion object {
            fun from(parent: ViewGroup): CommitViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = RecyclerItemRowBinding.inflate(layoutInflater, parent, false)
                return CommitViewHolder(binding)
            }
        }
    }

    fun setData(newData: List<Commits.CommitsItem>){
        val commitDiffUtil =
            CommitDiffUtil(receiveCommit, newData)
        val diffUtilResult = DiffUtil.calculateDiff(commitDiffUtil)
        receiveCommit = newData
        diffUtilResult.dispatchUpdatesTo(this)
    }


}