package com.git.closedpullrequests.model

import com.git.closedpullrequests.model.data.response.ClosedPullRequestResponse
import com.git.closedpullrequests.model.data.apiService.GitHubApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GitHubRepository @Inject constructor(
    private val apiService: GitHubApiService
) {

    suspend fun getClosedPullRequests(owner: String, repo: String): Flow<List<ClosedPullRequestResponse>> {
        return flow {
            val response = apiService.getClosedPullRequests(owner, repo)
            if (response.isSuccessful) {
                val closedPullRequests = response.body() ?: emptyList()
                emit(closedPullRequests)
            } else {
                val statusCode = response.code()
                throw Exception("Failed to fetch closed pull requests, Status code: $statusCode")
            }
        }.flowOn(Dispatchers.IO)
    }
}
