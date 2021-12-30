package com.easycoding.learningroomrelations.presentation.musiclibrarydetails

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import com.easycoding.learningroomrelations.R
import com.easycoding.learningroomrelations.databinding.ActivityMusicLibraryDetailsBinding
import com.easycoding.learningroomrelations.presentation.users.UsersAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MusicLibraryDetailsActivity : AppCompatActivity() {
    private var _binding: ActivityMusicLibraryDetailsBinding? = null
    private val binding: ActivityMusicLibraryDetailsBinding
        get() = _binding!!
    private val viewModel: MusicLibraryDetailsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = DataBindingUtil.setContentView(this, R.layout.activity_music_library_details)
        binding.lifecycleOwner = this
        binding.viewmodel = viewModel

        val adapter = UsersAdapter()
        binding.rvUsers.adapter = adapter

        viewModel.musicLibraryUsers.observe(this) {
            adapter.submitList(it.users)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
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