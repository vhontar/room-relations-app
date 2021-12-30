package com.easycoding.learningroomrelations.presentation.users

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.easycoding.learningroomrelations.databinding.RecyclerviewUserItemBinding
import com.easycoding.learningroomrelations.business.models.User

class UsersAdapter(
    private val listener: UserClickListener? = null
): ListAdapter<User, UserViewHolder>(UserDiffUtilItemCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder = UserViewHolder.from(parent, listener)
    override fun onBindViewHolder(holder: UserViewHolder, position: Int) = holder.bind(getItem(position))
}

class UserViewHolder private constructor(
    private val binding: RecyclerviewUserItemBinding
): RecyclerView.ViewHolder(binding.root) {
    private var listener: UserClickListener? = null
    private var localUser: User? = null

    init {
        binding.clUserRoot.setOnClickListener {
            localUser?.let {
                listener?.onUserClicked(it)
            }
        }
    }

    fun bind(item: User) {
        localUser = item
        binding.item = item
        binding.executePendingBindings()
    }

    companion object {
        fun from(parent: ViewGroup, listener: UserClickListener?): UserViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = RecyclerviewUserItemBinding.inflate(layoutInflater, parent,false)
            val holder = UserViewHolder(binding)
            holder.listener = listener
            return holder
        }
    }
}

class UserDiffUtilItemCallback: DiffUtil.ItemCallback<User>() {
    override fun areItemsTheSame(oldItem: User, newItem: User): Boolean = oldItem.userId == newItem.userId
    override fun areContentsTheSame(oldItem: User, newItem: User): Boolean = oldItem == newItem
}

interface UserClickListener {
    fun onUserClicked(user: User)
}