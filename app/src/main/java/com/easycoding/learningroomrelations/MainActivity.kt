package com.easycoding.learningroomrelations

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.easycoding.learningroomrelations.databinding.ActivityMainBinding
import com.easycoding.learningroomrelations.ui.comparetwousers.CompareTwoUsersActivity
import com.easycoding.learningroomrelations.ui.users.UsersActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.btnShowUsers.setOnClickListener {
            startActivity(UsersActivity.newIntent(this))
        }
        binding.btnCompareTwoUsers.setOnClickListener {
            startActivity(CompareTwoUsersActivity.newIntent(this))
        }
    }
}