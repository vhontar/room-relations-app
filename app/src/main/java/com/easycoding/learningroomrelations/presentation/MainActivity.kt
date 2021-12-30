package com.easycoding.learningroomrelations.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.easycoding.learningroomrelations.R
import com.easycoding.learningroomrelations.databinding.ActivityMainBinding
import com.easycoding.learningroomrelations.presentation.comparetwousers.CompareTwoUsersActivity
import com.easycoding.learningroomrelations.presentation.selectsong.SelectSongActivity
import com.easycoding.learningroomrelations.presentation.users.UsersActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.apply {
            btnShowUsers.setOnClickListener {
                startActivity(UsersActivity.newIntent(this@MainActivity))
            }
            btnCompareTwoUsers.setOnClickListener {
                startActivity(CompareTwoUsersActivity.newIntent(this@MainActivity))
            }
            btnSelectSong.setOnClickListener {
                 startActivity(SelectSongActivity.newIntent(this@MainActivity))
            }
        }

    }
}