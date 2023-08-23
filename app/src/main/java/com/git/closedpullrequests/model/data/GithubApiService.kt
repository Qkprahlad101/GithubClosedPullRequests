package com.git.closedpullrequests.model.data

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GitHubApiService {

    @GET("repos/{owner}/{repo}/pulls")
    suspend fun getClosedPullRequests(
        @Path("owner") owner: String,
        @Path("repo") repo: String,
        @Query("state") state: String = "closed"
    ): Response<List<ClosedPullRequest>>
}
