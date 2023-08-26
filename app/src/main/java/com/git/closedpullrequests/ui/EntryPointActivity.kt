package com.git.closedpullrequests.ui

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.git.closedpullrequests.ui.jetpack_ui.ClosedPullRequestsScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EntryPointActivity : AppCompatActivity() {
    private val viewModel: ClosedPullRequestsViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ClosedPullRequestsScreen(viewModel)
        }
    }
}
