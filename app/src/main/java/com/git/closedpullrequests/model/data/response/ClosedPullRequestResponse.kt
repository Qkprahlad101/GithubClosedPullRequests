package com.git.closedpullrequests.model.data.response

import com.google.gson.annotations.SerializedName

data class ClosedPullRequestResponse(
    @SerializedName("title") val title: String,
    @SerializedName("created_at") val createdDate: String,
    @SerializedName("closed_at") val closedDate: String = "",
    @SerializedName("user") val user: User
)

data class User(
    @SerializedName("login") val userName: String,
    @SerializedName("avatar_url") val imageUrl: String
)


object MockList{

    fun getClosedPullRequest(): List<ClosedPullRequestResponse>{
        val item1  = ClosedPullRequestResponse(title = "Added Feature 1", createdDate = "2023-08-21T16:15:34Z", closedDate="2023-08-21T16:15:42Z", user= User(userName="Qkprahlad101", imageUrl="https://avatars.githubusercontent.com/u/45912671?v=4"))

        val itemList: ArrayList<ClosedPullRequestResponse> = ArrayList()
        itemList.add(item1)
        return itemList
    }
}
