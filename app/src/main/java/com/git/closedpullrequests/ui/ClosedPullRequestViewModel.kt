package com.git.closedpullrequests.ui

import android.app.Application
import android.widget.Toast
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
    private val repository: GitHubRepository,
    private val application: Application
) : ViewModel() {

    private val _closedPullRequests = MutableLiveData<List<ClosedPullRequestResponse>>()
    val loaderVisibility = MutableLiveData(false)
    val closedPullRequests: LiveData<List<ClosedPullRequestResponse>>
        get() = _closedPullRequests

    private val _selectedClosedPullRequest = MutableLiveData<ClosedPullRequestResponse>()
    val selectedClosedPullRequest: LiveData<ClosedPullRequestResponse>
        get() = _selectedClosedPullRequest

    fun fetchClosedPullRequests(owner: String, repo: String) {
        loaderVisibility.value = true
        viewModelScope.launch {
            try {
                repository.getClosedPullRequests(owner, repo).collect {
                    _closedPullRequests.value = it
                }
            } catch (e: Exception) {
                Toast.makeText(application, "Error occured: $e ", Toast.LENGTH_SHORT).show()
            }finally {
                loaderVisibility.value = false
            }
        }
    }
}
