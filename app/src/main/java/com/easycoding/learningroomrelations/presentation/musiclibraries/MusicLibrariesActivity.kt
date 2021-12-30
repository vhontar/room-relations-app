package com.easycoding.learningroomrelations.presentation.musiclibraries

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import com.easycoding.learningroomrelations.R
import com.easycoding.learningroomrelations.databinding.ActivityMusicLibrariesBinding
import com.easycoding.learningroomrelations.business.models.MusicLibrary
import com.easycoding.learningroomrelations.presentation.musiclibrarydetails.MusicLibraryDetailsActivity
import com.easycoding.learningroomrelations.presentation.songs.SongsActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MusicLibrariesActivity : AppCompatActivity(), MusicLibraryClickListener {
    private var _binding: ActivityMusicLibrariesBinding? = null
    private val binding: ActivityMusicLibrariesBinding
        get() = _binding!!
    private val viewModel: MusicLibrariesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = DataBindingUtil.setContentView(this, R.layout.activity_music_libraries)

        val adapter = MusicLibrariesAdapter(this)
        binding.rvMusicLibraries.adapter = adapter

        viewModel.userMusicLibraries.observe(this) {
            adapter.submitList(it.musicLibraries)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onDetailsClicked(musicLibrary: MusicLibrary) {
        startActivity(MusicLibraryDetailsActivity.newIntent(this, musicLibrary.musicLibraryId))
    }

    override fun onSongsClicked(musicLibrary: MusicLibrary) {
        startActivity(SongsActivity.newIntent(this, musicLibrary.musicLibraryId))
    }

    companion object {
        fun newIntent(context: Context, userId: Int): Intent {
            val args = bundleOf(
                "userId" to userId
            )

            val intent = Intent(context, MusicLibrariesActivity::class.java)
            intent.putExtras(args)
            return intent
        }
    }
}