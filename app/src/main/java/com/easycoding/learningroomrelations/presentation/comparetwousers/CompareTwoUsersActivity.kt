package com.easycoding.learningroomrelations.presentation.comparetwousers

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
import com.easycoding.learningroomrelations.databinding.ActivityCompareTwoUsersBinding
import com.easycoding.learningroomrelations.business.models.User
import com.easycoding.learningroomrelations.presentation.musiclibraries.MusicLibrariesAdapter
import com.easycoding.learningroomrelations.presentation.songs.SongsAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CompareTwoUsersActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCompareTwoUsersBinding
    private val viewModel: CompareTwoUsersViewModel by viewModels()

    private var spinnerAdapter1: ArrayAdapter<User>? = null
    private var spinnerAdapter2: ArrayAdapter<User>? = null

    private var musicLibraryAdapter: MusicLibrariesAdapter? = null
    private var songsAdapter: SongsAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_compare_two_users)
        binding.apply {
            lifecycleOwner = this@CompareTwoUsersActivity
            viewmodel = viewModel

            spinnerAdapter1 = ArrayAdapter(this@CompareTwoUsersActivity, R.layout.spinner_item)
            spUser1.adapter = spinnerAdapter1
            spUser1.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    viewModel.selectFirstUser(parent?.selectedItem as User?)
                }
                override fun onNothingSelected(parent: AdapterView<*>?) {}
            }

            spinnerAdapter2 = ArrayAdapter(this@CompareTwoUsersActivity, R.layout.spinner_item)
            spUser2.adapter = spinnerAdapter2
            spUser2.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    viewModel.selectSecondUser(parent?.selectedItem as User?)
                }
                override fun onNothingSelected(parent: AdapterView<*>?) {}
            }

            musicLibraryAdapter = MusicLibrariesAdapter(hideButtons = true)
            rvMusicLibraries.adapter = musicLibraryAdapter

            songsAdapter = SongsAdapter()
            rvSongs.adapter = songsAdapter
        }

        viewModel.apply {
            users.observe(this@CompareTwoUsersActivity) {
                spinnerAdapter1?.addAll(it)
                spinnerAdapter2?.addAll(it)
            }

            similarMusicLibraries.observe(this@CompareTwoUsersActivity) {
                musicLibraryAdapter?.submitList(it)
            }

            similarSongs.observe(this@CompareTwoUsersActivity) {
                songsAdapter?.submitList(it)
            }
        }
    }

    companion object {
        fun newIntent(context: Context) = Intent(context, CompareTwoUsersActivity::class.java)
    }
}