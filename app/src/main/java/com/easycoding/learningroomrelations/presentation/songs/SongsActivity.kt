package com.easycoding.learningroomrelations.presentation.songs

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import com.easycoding.learningroomrelations.R
import com.easycoding.learningroomrelations.databinding.ActivitySongsBinding
import com.easycoding.learningroomrelations.business.models.Song
import com.easycoding.learningroomrelations.presentation.songdetails.SongDetailsActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SongsActivity : AppCompatActivity(), SongClickListener {
    private var _binding: ActivitySongsBinding? = null
    private val binding: ActivitySongsBinding
        get() = _binding!!
    private val viewModel: SongsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = DataBindingUtil.setContentView(this, R.layout.activity_songs)

        val adapter = SongsAdapter(this)
        binding.rvSongs.adapter = adapter

        viewModel.musicLibrarySongs.observe(this) {
            adapter.submitList(it.songs)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onSongClicked(song: Song) {
        startActivity(SongDetailsActivity.newIntent(this, song.songId))
    }

    companion object {
        fun newIntent(context: Context, musicLibraryId: Int): Intent {
            val args = bundleOf(
                "musicLibraryId" to musicLibraryId
            )

            val intent = Intent(context, SongsActivity::class.java)
            intent.putExtras(args)
            return intent
        }
    }
}