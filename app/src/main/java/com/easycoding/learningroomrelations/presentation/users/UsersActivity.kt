package com.easycoding.learningroomrelations.presentation.users

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.easycoding.learningroomrelations.R
import com.easycoding.learningroomrelations.databinding.ActivityUsersBinding
import com.easycoding.learningroomrelations.business.models.User
import com.easycoding.learningroomrelations.presentation.musiclibraries.MusicLibrariesActivity
import com.easycoding.learningroomrelations.presentation.users.adapter.UserClickListener
import com.easycoding.learningroomrelations.presentation.users.adapter.UsersAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UsersActivity : AppCompatActivity(), UserClickListener {
    private var _binding: ActivityUsersBinding? = null
    private val binding: ActivityUsersBinding
        get() = _binding!!
    private val viewModel: UsersViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = DataBindingUtil.setContentView(this, R.layout.activity_users)

        val adapter = UsersAdapter(this)
        binding.rvUsers.adapter = adapter

        viewModel.users.observe(this) {
            adapter.submitList(it)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onUserClicked(user: User) {
        startActivity(MusicLibrariesActivity.newIntent(this, user.userId))
    }

    companion object {
        fun newIntent(context: Context) = Intent(context, UsersActivity::class.java)
    }
}