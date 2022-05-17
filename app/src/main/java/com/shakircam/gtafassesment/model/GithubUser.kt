package com.shakircam.gtafassesment.model

import android.annotation.SuppressLint
import com.google.gson.annotations.SerializedName
import java.text.SimpleDateFormat
import java.util.*

data class GithubUser(

    @SerializedName("avatar_url") var avatarUrl: String?,
    @SerializedName("name") var name: String?,
    @SerializedName("location") var location: String?,
    @SerializedName("bio") var bio: String?,
    @SerializedName("public_repos") var publicRepos: Int?,
    @SerializedName("public_gists") var publicGists: Int?

)
