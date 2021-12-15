package com.easycoding.learningroomrelations.ui.musiclibraries

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import com.easycoding.learningroomrelations.R
import com.easycoding.learningroomrelations.databinding.ActivityMusicLibrariesBinding
import com.easycoding.learningroomrelations.models.MusicLibrary
import com.easycoding.learningroomrelations.ui.musiclibrarydetails.MusicLibraryDetailsActivity
import com.easycoding.learningroomrelations.ui.songs.SongsActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MusicLibrariesActivity : AppCompatActivity(), MusicLibraryClickListener {
    private lateinit var binding: ActivityMusicLibrariesBinding
    private val viewModel: MusicLibrariesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_music_libraries)

        val adapter = MusicLibrariesAdapter(this)
        binding.rvMusicLibraries.adapter = adapter

        viewModel.userMusicLibraries.observe(this) {
            adapter.submitList(it.musicLibraries)
        }
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