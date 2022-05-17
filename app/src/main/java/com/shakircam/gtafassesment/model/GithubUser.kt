package com.shakircam.gtafassesment.model

import android.annotation.SuppressLint
import com.google.gson.annotations.SerializedName
import java.text.SimpleDateFormat
import java.util.*

data class GithubUser(
     var avatar_url: String?,
     var name: String?,
     var location: String?,
     var bio: String?,
     var public_repos: Int?,
     var public_gists: Int?
)
