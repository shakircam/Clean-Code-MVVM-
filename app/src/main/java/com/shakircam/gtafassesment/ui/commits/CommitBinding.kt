package com.shakircam.gtafassesment.ui.commits

import android.annotation.SuppressLint
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import coil.load
import com.bumptech.glide.Glide
import com.shakircam.gtafassesment.R
import com.shakircam.gtafassesment.model.GithubUser
import java.text.SimpleDateFormat


class CommitBinding {

    companion object {

        @BindingAdapter("loadImageFromUrl")
        @JvmStatic
        fun loadImageFromUrl(imageView: ImageView, imageUrl: String?) {
            imageView.load(imageUrl) {
                crossfade(600)
            }
        }


        @BindingAdapter("loadUserImage")
        @JvmStatic
        fun loadUserImage(imageView: ImageView, imageUrl: String?) {
            Glide.with(imageView.context)
                .load(imageUrl)
                .placeholder(R.drawable.profile)
                .error(R.drawable.profile)
                .into(imageView)
        }


        @SuppressLint("SimpleDateFormat")
        @BindingAdapter("timeFormat")
        @JvmStatic
        fun timeFormat(textView: TextView, date: String) {

            val parser = SimpleDateFormat("yyyy-MM-dd")
            val formatter = SimpleDateFormat("MM/dd/yyyy")
            val formattedDate = formatter.format(parser.parse(date)!!)
            textView.text = formattedDate
        }


        @SuppressLint("SetTextI18n")
        @BindingAdapter("repo")
        @JvmStatic
        fun TextView.setRepo(repo: GithubUser?) {
            this.text =
                "${this.context.getString(R.string.repo)} ${repo?.public_repos.toString()}"
        }

        @SuppressLint("SetTextI18n")
        @BindingAdapter("gist")
        @JvmStatic
        fun TextView.setGist(gist: GithubUser?) {
            this.text = "${this.context.getString(R.string.gist)} ${gist?.public_gists.toString()}"
        }

        @SuppressLint("SetTextI18n")
        @BindingAdapter("bio")
        @JvmStatic
        fun TextView.setBio(bio: GithubUser?) {
            this.text = "${this.context.getString(R.string.bio)} ${bio?.bio.toString()}"
        }

          @BindingAdapter("onClickListener")
        @JvmStatic
        fun onClick(rowLayout: ConstraintLayout){
            rowLayout.setOnClickListener {
                try {

                } catch (e: Exception) {
                    Log.d("onCommitItemClick", e.toString())
                }
        }
    }

    }
}