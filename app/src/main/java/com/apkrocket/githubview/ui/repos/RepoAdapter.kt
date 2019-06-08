package com.apkrocket.githubview.ui.repos

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.apkrocket.githubview.R
import com.apkrocket.githubview.store.model.Repo
import com.apkrocket.githubview.ui.repos.RepoAdapter.RepoViewHolder
import kotlinx.android.synthetic.main.item_repo.view.*

class RepoAdapter : RecyclerView.Adapter<RepoViewHolder>() {

    private val items = mutableListOf<Repo>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        return RepoViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_repo, parent, false))
    }

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        holder.bindItem(getItem(position))
    }

    override fun getItemCount() = items.size

    private fun getItem(position: Int) = items[position]

    fun setItems(items: List<Repo>) {
        if (this.items.size != items.size || !this.items.containsAll(items)) {
            this.items.clear()
            this.items.addAll(items)
            notifyDataSetChanged()
        }
    }

    inner class RepoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindItem(repo: Repo) {
            itemView.repo_name.text = repo.name
            itemView.description.text = repo.description
            itemView.owner_name.text = repo.owner?.login
        }
    }
}