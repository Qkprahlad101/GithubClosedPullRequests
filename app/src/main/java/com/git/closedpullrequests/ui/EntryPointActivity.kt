package com.git.closedpullrequests.ui

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.git.closedpullrequests.R
import com.git.closedpullrequests.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EntryPointActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: ClosedPullRequestsViewModel by viewModels()
    private var adapter = PullRequestAdapter(emptyList())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            this.adapter = adapter
        }


        binding.fetchButton.setOnClickListener {
            val owner = binding.ownerEditText.text.toString().trimEnd()
            val repo = binding.repoEditText.text.toString().trimEnd()

            if (owner.isEmpty() || repo.isEmpty()) {
                Toast.makeText(this, "Please enter both fields", Toast.LENGTH_SHORT).show()
            } else {
                viewModel.fetchClosedPullRequests(owner, repo)
            }
        }

//        viewModel.fetchClosedPullRequests("Qkprahlad101", "GithubClosedPullRequests") //initial data
        observer()
    }

    private fun observer() {
        val spacing = resources.getDimensionPixelSize(R.dimen.item_spacing)
        viewModel.closedPullRequests.observe(this) { closedPullRequests ->
            adapter = PullRequestAdapter(closedPullRequests)
            adapter.setClosedPullRequests(closedPullRequests)
            adapter.notifyDataSetChanged()
            binding.recyclerView.adapter = adapter
            binding.recyclerView.addItemDecoration(ItemSpacingDecoration(spacing))
        }
    }

}
