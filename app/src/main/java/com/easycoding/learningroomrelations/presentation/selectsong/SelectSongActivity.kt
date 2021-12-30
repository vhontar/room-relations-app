package com.easycoding.learningroomrelations.presentation.selectsong

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.easycoding.learningroomrelations.R
import com.easycoding.learningroomrelations.databinding.ActivitySelectSongBinding
import com.easycoding.learningroomrelations.business.models.Song
import com.easycoding.learningroomrelations.presentation.musiclibraries.MusicLibrariesAdapter
import com.easycoding.learningroomrelations.presentation.users.UsersAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SelectSongActivity : AppCompatActivity() {
    private var _binding: ActivitySelectSongBinding? = null
    private val binding: ActivitySelectSongBinding
        get() = _binding!!
    private val viewModel: SelectSongViewModel by viewModels()

    private var spinnerSongAdapter: ArrayAdapter<Song>? = null
    private var musicLibrariesAdapter: MusicLibrariesAdapter? = null
    private var usersAdapter: UsersAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = DataBindingUtil.setContentView(this, R.layout.activity_select_song)
        binding.apply {
            spinnerSongAdapter = ArrayAdapter(this@SelectSongActivity, R.layout.spinner_item)
            spSong.adapter = spinnerSongAdapter
            spSong.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    viewModel.selectSong(p0?.selectedItem as Song?)
                }
                override fun onNothingSelected(p0: AdapterView<*>?) { }
            }

            musicLibrariesAdapter = MusicLibrariesAdapter(hideButtons = true)
            rvMusicLibraries.adapter = musicLibrariesAdapter

            usersAdapter = UsersAdapter()
            rvUsers.adapter = usersAdapter
        }

        viewModel.apply {
            songs.observe(this@SelectSongActivity) {
                spinnerSongAdapter?.addAll(it)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, SelectSongActivity::class.java)
        }
    }
}