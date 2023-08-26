package com.git.closedpullrequests.ui.jetpack_ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import coil.compose.rememberImagePainter
import com.git.closedpullrequests.model.data.response.ClosedPullRequestResponse

@Composable
fun ClosedPullRequestsDetails(pullRequest: ClosedPullRequestResponse){

    Surface(color = MaterialTheme.colors.background) {
        Row {
            Column(){
                Image(
                    painter = rememberImagePainter(data = pullRequest.user.imageUrl),
                    contentDescription = "My Image",
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }

}