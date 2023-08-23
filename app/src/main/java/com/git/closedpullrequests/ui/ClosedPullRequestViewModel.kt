package com.git.closedpullrequests.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.git.closedpullrequests.model.GitHubRepository
import com.git.closedpullrequests.model.data.ClosedPullRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ClosedPullRequestsViewModel @Inject constructor(
    private val repository: GitHubRepository
) : ViewModel() {

    private val _closedPullRequests = MutableLiveData<List<ClosedPullRequest>>()
    val closedPullRequests: LiveData<List<ClosedPullRequest>>
        get() = _closedPullRequests

    fun fetchClosedPullRequests(owner: String, repo: String) {
        viewModelScope.launch {
            try {
                repository.getClosedPullRequests(owner, repo).collect {
                    _closedPullRequests.value = it
                }
            } catch (e: Exception) {
                Log.d("ViewModel Error", "Error: $e")
            }
        }
    }
}
