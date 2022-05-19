package com.shakircam.gtafassesment.utils

import android.annotation.SuppressLint
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import coil.load
import com.bumptech.glide.Glide
import com.shakircam.gtafassesment.R
import com.shakircam.gtafassesment.model.GithubUser
import java.text.SimpleDateFormat

class BindingData {

    companion object{


        /** ----------- data binding for github commits ---------- */

        @BindingAdapter("loadImageFromUrl")
        @JvmStatic
        fun loadImageFromUrl(imageView: ImageView, imageUrl: String?) {
            imageView.load(imageUrl) {
                crossfade(600)
            }
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



        /** ----------- data binding for github user profile ---------- */

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