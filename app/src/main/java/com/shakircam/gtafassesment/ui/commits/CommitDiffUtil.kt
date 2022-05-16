package com.shakircam.gtafassesment.ui.commits

import androidx.recyclerview.widget.DiffUtil
import com.shakircam.gtafassesment.model.Commits

class CommitDiffUtil(
    private val oldList: List<Commits.CommitsItem>,
    private val newList: List<Commits.CommitsItem>
): DiffUtil.Callback() {


    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] === newList[newItemPosition]
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}