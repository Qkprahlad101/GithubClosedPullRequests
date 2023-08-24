package com.git.closedpullrequests.ui.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.git.closedpullrequests.R
import com.git.closedpullrequests.utils.formatDate
import com.git.closedpullrequests.model.data.response.ClosedPullRequestResponse
import com.git.closedpullrequests.ui.ClosedPullRequestDetailsActivity

class PullRequestAdapter(private val context: Context, private var pullRequests: List<ClosedPullRequestResponse>) : RecyclerView.Adapter<PullRequestAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_pull_request, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val pullRequest = pullRequests[position]
        holder.bind(pullRequest)
        holder.itemView.setOnClickListener {
            val intent = Intent(context, ClosedPullRequestDetailsActivity::class.java)
            intent.putExtra("closedPullRequest", pullRequest)
            context.startActivity(intent)
        }
    }

    fun setClosedPullRequests(closedPullRequests: List<ClosedPullRequestResponse>) {
        this.pullRequests = closedPullRequests
    }

    override fun getItemCount(): Int {
        return pullRequests.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val titleTextView: TextView = itemView.findViewById(R.id.titleTextView)
        private val createdDateTextView: TextView = itemView.findViewById(R.id.createdDateTextView)
        private val closedDateTextView: TextView = itemView.findViewById(R.id.closedDateTextView)
        private val userNameTextView: TextView = itemView.findViewById(R.id.userNameTextView)
        private val userImageView: ImageView = itemView.findViewById(R.id.userImageView)

        fun bind(pullRequest: ClosedPullRequestResponse) {
            titleTextView.text = "Title: "+ pullRequest.title
            createdDateTextView.text = "Created Date: " +  formatDate(pullRequest.createdDate)
            closedDateTextView.text = "Closed Date: " + formatDate(pullRequest.closedDate)
            userNameTextView.text = "User Name: "+ pullRequest.user.userName
            Glide.with(itemView.context)
                .load(pullRequest.user.imageUrl)
                .into(userImageView)
        }
    }
}
