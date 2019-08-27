package com.example.android.presentation

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.android.R
import com.example.android.presentation.model.User
import kotlinx.android.synthetic.main.item_user.view.*

class SearchAdapter(var context: Context, val items: ArrayList<User>): RecyclerView.Adapter<SearchAdapter.UserListHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserListHolder {
        return UserListHolder(LayoutInflater.from(context).inflate(R.layout.item_user, parent, false))
    }

    override fun onBindViewHolder(holder: UserListHolder, position: Int) {
        holder.run {
            items[position].let {
                bindView(it)
            }
        }
    }

    override fun getItemCount() = items.size

    inner class UserListHolder(val view: View): RecyclerView.ViewHolder(view) {

        fun bindView(userListItem: User) {
            with(view) {
                tvItemUserName.text = userListItem.login
                tvItemUserUrl.text = userListItem.htmlUrl

                Glide.with(context)
                    .load(userListItem.avatarUrl)
                    .into(ivItemUserProfile)
            }
        }
    }

}
