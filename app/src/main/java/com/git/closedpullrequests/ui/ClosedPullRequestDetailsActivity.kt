package com.git.closedpullrequests.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.git.closedpullrequests.R
import com.git.closedpullrequests.databinding.ActivityClosedPullRequestDetailsBinding
import com.git.closedpullrequests.model.data.response.ClosedPullRequestResponse
import com.git.closedpullrequests.utils.formatDate
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ClosedPullRequestDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityClosedPullRequestDetailsBinding
    private val viewModel: ClosedPullRequestsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_closed_pull_request_details)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        val closedPullRequest = intent.getParcelableExtra<ClosedPullRequestResponse>("closedPullRequest")

        closedPullRequest?.let {
            binding.titleTextView.text = "Title: "+ it.title
            binding.userNameTextView.text = "User Name: "+ it.user.userName
            binding.createdDateTextView.text = "Created Date: " + formatDate(it.createdDate)
            binding.closedDateTextView.text = "Closed Date: " + formatDate(it.closedDate)
            // Load the image using a library like Glide or Picasso
            // Example with Glide:
//            Glide.with(this)
//                .load(it.user.imageUrl)
//                .into(binding.userImageView)
        }
    }
}