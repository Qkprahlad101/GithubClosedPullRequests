package com.git.closedpullrequests.model.data.response

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class ClosedPullRequestResponse(
    @SerializedName("title") val title: String,
    @SerializedName("created_at") val createdDate: String,
    @SerializedName("closed_at") val closedDate: String = "",
    @SerializedName("user") val user: User
): Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readParcelable(User::class.java.classLoader) ?: User("", "")
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeString(createdDate)
        parcel.writeString(closedDate)
        parcel.writeParcelable(user, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ClosedPullRequestResponse> {
        override fun createFromParcel(parcel: Parcel): ClosedPullRequestResponse {
            return ClosedPullRequestResponse(parcel)
        }

        override fun newArray(size: Int): Array<ClosedPullRequestResponse?> {
            return arrayOfNulls(size)
        }
    }
}

data class User(
    @SerializedName("login") val userName: String,
    @SerializedName("avatar_url") val imageUrl: String
): Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: ""
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(userName)
        parcel.writeString(imageUrl)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<User> {
        override fun createFromParcel(parcel: Parcel): User {
            return User(parcel)
        }

        override fun newArray(size: Int): Array<User?> {
            return arrayOfNulls(size)
        }
    }
}


object MockList{

    fun getClosedPullRequest(): List<ClosedPullRequestResponse>{
        val item1  = ClosedPullRequestResponse(title = "Added Feature 1", createdDate = "2023-08-21T16:15:34Z", closedDate="2023-08-21T16:15:42Z", user= User(userName="Qkprahlad101", imageUrl="https://avatars.githubusercontent.com/u/45912671?v=4"))

        val itemList: ArrayList<ClosedPullRequestResponse> = ArrayList()
        itemList.add(item1)
        return itemList
    }
}
