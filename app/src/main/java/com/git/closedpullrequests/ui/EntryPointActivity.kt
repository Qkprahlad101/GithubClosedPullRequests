package com.git.closedpullrequests.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.git.closedpullrequests.R
import com.git.closedpullrequests.databinding.ActivityMainBinding
import com.git.closedpullrequests.ui.adapter.PullRequestAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EntryPointActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: ClosedPullRequestsViewModel by viewModels()
    private var adapter = PullRequestAdapter(this,emptyList())

    private val spacing: Int = 12

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            this.adapter = adapter
        }
        binding.recyclerView.addItemDecoration(ItemSpacingDecoration(spacing))

        viewModel.fetchClosedPullRequests("Qkprahlad101", "GithubClosedPullRequests") //initial data
        observer()
    }

    private fun observer() {

        viewModel.closedPullRequests.observe(this) { closedPullRequests ->
            adapter = PullRequestAdapter(this, closedPullRequests)
            adapter.setClosedPullRequests(closedPullRequests)
            adapter.notifyDataSetChanged()
            binding.recyclerView.adapter = adapter
        }

        viewModel.loaderVisibility.observe(this){
            if(it) binding.progressBar.visibility = View.VISIBLE else binding.progressBar.visibility = View.GONE
        }

        binding.fetchButton.setOnClickListener {
            val owner = binding.ownerEditText.text.toString().trimEnd()
            val repo = binding.repoEditText.text.toString().trimEnd()

            if (owner.isEmpty() || repo.isEmpty()) {
                Toast.makeText(this, "Please enter both fields!!", Toast.LENGTH_SHORT).show()
            } else {
                viewModel.fetchClosedPullRequests(owner, repo)
            }
        }
    }
}
