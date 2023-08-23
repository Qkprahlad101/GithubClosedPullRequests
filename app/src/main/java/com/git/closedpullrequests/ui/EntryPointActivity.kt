package com.git.closedpullrequests.ui

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.git.closedpullrequests.R
import com.git.closedpullrequests.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EntryPointActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: ClosedPullRequestsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)

        val recycler = findViewById<RecyclerView>(R.id.recyclerView)
        val fetchButton = findViewById<Button>(R.id.fetchButton)
        val ownerEditText = findViewById<EditText>(R.id.ownerEditText)
        val repoEditText = findViewById<EditText>(R.id.repoEditText)
        var adapter = PullRequestAdapter(emptyList())
        val spacing = resources.getDimensionPixelSize(R.dimen.item_spacing)
        recycler.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            this.adapter = adapter
        }

        fetchButton.setOnClickListener {
            val owner = ownerEditText.text.toString().trimEnd()
            val repo = repoEditText.text.toString().trimEnd()
            viewModel.fetchClosedPullRequests(owner, repo)
        }

        viewModel.fetchClosedPullRequests("Qkprahlad101", "GithubClosedPullRequests")

        viewModel.closedPullRequests.observe(this) { closedPullRequests ->
            adapter = PullRequestAdapter(closedPullRequests)
            adapter.setClosedPullRequests(closedPullRequests)
            adapter.notifyDataSetChanged()
            recycler.adapter = adapter
            recycler.addItemDecoration(ItemSpacingDecoration(spacing))
        }
    }
}
