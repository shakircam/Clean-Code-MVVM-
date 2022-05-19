package com.shakircam.gtafassesment.ui.profile

import android.annotation.SuppressLint
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.shakircam.gtafassesment.R
import com.shakircam.gtafassesment.model.GithubUser

class ProfileBinding {

    companion object{


        @BindingAdapter("loadUserImage")
        @JvmStatic
        fun loadUserImage(imageView: ImageView, imageUrl: String?) {
            Glide.with(imageView.context)
                .load(imageUrl)
                .placeholder(R.drawable.profile)
                .error(R.drawable.profile)
                .into(imageView)
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

    }
}