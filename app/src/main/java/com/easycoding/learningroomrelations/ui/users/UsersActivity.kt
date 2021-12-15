package com.easycoding.learningroomrelations.ui.users

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.easycoding.learningroomrelations.R
import com.easycoding.learningroomrelations.database.dao.UserDao
import com.easycoding.learningroomrelations.databinding.ActivityUsersBinding
import com.easycoding.learningroomrelations.models.User
import com.easycoding.learningroomrelations.ui.musiclibraries.MusicLibrariesActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class UsersActivity : AppCompatActivity(), UserClickListener {
    private lateinit var binding: ActivityUsersBinding
    private val viewModel: UsersViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_users)

        val adapter = UsersAdapter(this)
        binding.rvUsers.adapter = adapter

        viewModel.users.observe(this) {
            adapter.submitList(it)
        }

    }

    override fun onUserClicked(user: User) {
        startActivity(MusicLibrariesActivity.newIntent(this, user.userId))
    }

    companion object {
        fun newIntent(context: Context) = Intent(context, UsersActivity::class.java)
    }
}