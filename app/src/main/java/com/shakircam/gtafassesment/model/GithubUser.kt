package com.shakircam.gtafassesment.model

import android.annotation.SuppressLint
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import java.text.SimpleDateFormat
import java.util.*

@Parcelize
data class GithubUser(
     var avatar_url: String?,
     var name: String?,
     var location: String?,
     var bio: String?,
     var public_repos: Int?,
     var public_gists: Int?
): Parcelable
