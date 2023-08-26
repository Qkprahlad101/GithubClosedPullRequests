package com.git.closedpullrequests.ui.jetpack_ui

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.git.closedpullrequests.model.data.response.ClosedPullRequestResponse
import com.git.closedpullrequests.ui.ClosedPullRequestsViewModel
import com.git.closedpullrequests.utils.formatDate
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

@Composable
fun ClosedPullRequestsScreen(viewModel: ClosedPullRequestsViewModel) {
    val response = viewModel.fetchClosedPullRequests("Qkprahlad101", "GithubClosedPullRequests")
    val closedPullRequests = viewModel.closedPullRequests.value
    Column {
        // UI elements for owner and repo fields
        // UI element for fetch button


        closedPullRequests.let {
            if (it.isNotEmpty()) {
                LazyColumn {
                    items(it) { pullRequest ->
                        // UI element for each closed pull request item
                        ListOfClosedPR(pullRequest = pullRequest)
                    }
                }
            } else {
                // UI element for empty state
                Text(text = "Data Not available", color = Color.Yellow)
            }
        }


        LaunchedEffect(viewModel.closedPullRequests) {
            viewModel.fetchClosedPullRequests("Qkprahlad101", "GithubClosedPullRequests")
        }
    }
}


@Composable
fun ListOfClosedPR(pullRequest: ClosedPullRequestResponse) {
    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .animateContentSize()
                .padding(16.dp)
        ) {
            Image(
                painter = rememberImagePainter(data = pullRequest.user.imageUrl),
                contentDescription = "My Image",
                modifier = Modifier
                    .size(48.dp)
                    .padding(end = 16.dp)
            )
            Column {
                Text(
                    text = pullRequest.title,
                    style = MaterialTheme.typography.h5
                )
                Text(
                    text = pullRequest.user.userName,
                    style = MaterialTheme.typography.h5
                )
                Text(
                    text = formatDate(pullRequest.createdDate),
                    style = MaterialTheme.typography.h6
                )
                Text(
                    text = formatDate(pullRequest.closedDate),
                    style = MaterialTheme.typography.h6
                )
            }
        }
    }
}

