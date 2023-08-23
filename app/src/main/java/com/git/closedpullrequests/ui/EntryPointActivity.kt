package com.git.closedpullrequests.ui

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
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
    var adapter = PullRequestAdapter(emptyList())

    private lateinit var recycler: RecyclerView
    private lateinit var fetchButton: Button
    private lateinit var ownerEditText: EditText
    private lateinit var repoEditText: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)

        recycler = findViewById(R.id.recyclerView)
        fetchButton = findViewById(R.id.fetchButton)
        ownerEditText = findViewById(R.id.ownerEditText)
        repoEditText = findViewById(R.id.repoEditText)

        recycler.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            this.adapter = adapter
        }


        fetchButton.setOnClickListener {
            val owner = ownerEditText.text.toString().trimEnd()
            val repo = repoEditText.text.toString().trimEnd()

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
            recycler.adapter = adapter
            recycler.addItemDecoration(ItemSpacingDecoration(spacing))
        }
    }

}
