package com.easycoding.learningroomrelations.presentation.songdetails

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import com.easycoding.learningroomrelations.R
import com.easycoding.learningroomrelations.databinding.ActivitySongDetailsBinding
import com.easycoding.learningroomrelations.presentation.musiclibraries.MusicLibrariesAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SongDetailsActivity : AppCompatActivity() {
    private var _binding: ActivitySongDetailsBinding? = null
    private val binding: ActivitySongDetailsBinding
        get() = _binding!!
    private val viewModel: SongDetailsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = DataBindingUtil.setContentView(this, R.layout.activity_song_details)
        binding.lifecycleOwner = this
        binding.viewmodel = viewModel

        val adapter = MusicLibrariesAdapter(hideButtons = true)
        binding.rvMusicLibraries.adapter = adapter

        viewModel.songMusicLibraries.observe(this) {
            adapter.submitList(it.musicLibraries)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        fun newIntent(context: Context, songId: Int): Intent {
            val args = bundleOf(
                "songId" to songId
            )

            val intent = Intent(context, SongDetailsActivity::class.java)
            intent.putExtras(args)
            return intent
        }
    }
}