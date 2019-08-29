package com.example.android.presentation.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.android.R
import com.example.android.presentation.model.User
import kotlinx.android.synthetic.main.item_user.view.*

class RepoAdapter(val context: Context?, val items: ArrayList<User>): RecyclerView.Adapter<RepoAdapter.RepoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        return RepoViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false))
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        holder.run{
            items[position].let {
                bind(items[position])
            }
        }
    }
    inner class RepoViewHolder(val view: View): RecyclerView.ViewHolder(view) {

        fun bind(user: User) {
            with(view) {
                com.bumptech.glide.Glide.with(context!!)
                    .load(user.avatarUrl)
                    .into(ivItemUserProfile)
            }
        }
    }
}