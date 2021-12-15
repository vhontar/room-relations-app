package com.easycoding.learningroomrelations.ui.comparetwousers

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.easycoding.learningroomrelations.R
import com.easycoding.learningroomrelations.databinding.ActivityCompareTwoUsersBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CompareTwoUsersActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCompareTwoUsersBinding
    private val viewModel: CompareTwoUsersViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_compare_two_users)
        // binding.spUser1.adapter =
    }

    companion object {
        fun newIntent(context: Context) = Intent(context, CompareTwoUsersActivity::class.java)
    }
}