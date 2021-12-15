package com.easycoding.learningroomrelations.ui.musiclibrarydetails

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import com.easycoding.learningroomrelations.R
import com.easycoding.learningroomrelations.databinding.ActivityMusicLibraryDetailsBinding
import com.easycoding.learningroomrelations.ui.users.UsersAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MusicLibraryDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMusicLibraryDetailsBinding
    private val viewModel: MusicLibraryDetailsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_music_library_details)
        binding.lifecycleOwner = this
        binding.viewmodel = viewModel

        val adapter = UsersAdapter()
        binding.rvUsers.adapter = adapter

        viewModel.musicLibraryUsers.observe(this) {
            adapter.submitList(it.users)
        }
    }

    companion object {
        fun newIntent(context: Context, musicLibraryId: Int): Intent {
            val args = bundleOf(
                "musicLibraryId" to musicLibraryId
            )

            val intent = Intent(context, MusicLibraryDetailsActivity::class.java)
            intent.putExtras(args)
            return intent
        }
    }
}