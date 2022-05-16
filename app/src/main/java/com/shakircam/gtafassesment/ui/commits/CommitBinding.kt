package com.shakircam.gtafassesment.ui.commits

import android.annotation.SuppressLint
import android.os.Build
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.databinding.BindingAdapter
import coil.load
import java.text.SimpleDateFormat


class CommitBinding {

    companion object{

        @BindingAdapter("loadImageFromUrl")
        @JvmStatic
        fun loadImageFromUrl(imageView: ImageView, imageUrl: String) {
            imageView.load(imageUrl) {
                crossfade(600)
            }
        }

        @RequiresApi(Build.VERSION_CODES.O)
        @SuppressLint("SimpleDateFormat")
        @BindingAdapter("timeFormat")
        @JvmStatic
        fun timeFormat(textView: TextView,date:String){

            val parser =  SimpleDateFormat("yyyy-MM-dd")
            val formatter = SimpleDateFormat("MM/dd/yyyy")
            val formattedDate = formatter.format(parser.parse(date)!!)
            textView.text = formattedDate
        }
    }
}