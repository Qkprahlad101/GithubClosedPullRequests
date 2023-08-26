package com.git.closedpullrequests.ui

import android.app.Application
import android.widget.Toast
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.git.closedpullrequests.model.GitHubRepository
import com.git.closedpullrequests.model.data.response.ClosedPullRequestResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ClosedPullRequestsViewModel @Inject constructor(
    private val repository: GitHubRepository
) : ViewModel() {
    private val _closedPullRequests = mutableStateOf(emptyList<ClosedPullRequestResponse>())
    val closedPullRequests: State<List<ClosedPullRequestResponse>> = _closedPullRequests

    fun fetchClosedPullRequests(owner: String, repo: String) {

        viewModelScope.launch {
            try {
                repository.getClosedPullRequests("Qkprahlad101", "GithubClosedPullRequests").collect{
                    _closedPullRequests.value = it
                }
            } catch (e: Exception) {
                // Handle error
            }
        }
    }
}