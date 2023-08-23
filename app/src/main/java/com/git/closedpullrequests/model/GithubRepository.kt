package com.git.closedpullrequests.model

import com.git.closedpullrequests.model.data.ClosedPullRequest
import com.git.closedpullrequests.model.data.GitHubApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GitHubRepository @Inject constructor(
    private val apiService: GitHubApiService
) {

    suspend fun getClosedPullRequests(owner: String, repo: String): Flow<List<ClosedPullRequest>> {
        return flow {
            val response = apiService.getClosedPullRequests(owner, repo)
            if (response.isSuccessful) {
                val closedPullRequests = response.body() ?: emptyList()
                emit(closedPullRequests)
            } else {
                throw Exception("Failed to fetch closed pull requests")
            }
        }.flowOn(Dispatchers.IO)
    }
}
