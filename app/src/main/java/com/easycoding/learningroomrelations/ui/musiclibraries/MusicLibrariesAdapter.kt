package com.easycoding.learningroomrelations.ui.musiclibraries

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.easycoding.learningroomrelations.databinding.RecyclerviewMusicLibraryItemBinding
import com.easycoding.learningroomrelations.models.MusicLibrary

class MusicLibrariesAdapter(
    private val listener: MusicLibraryClickListener? = null,
    private val hideButtons: Boolean = false
): ListAdapter<MusicLibrary, MusicLibraryViewHolder>(UserDiffUtilItemCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MusicLibraryViewHolder = MusicLibraryViewHolder.from(parent, listener, hideButtons)
    override fun onBindViewHolder(holder: MusicLibraryViewHolder, position: Int) = holder.bind(getItem(position))
}

class MusicLibraryViewHolder private constructor(
    private val binding: RecyclerviewMusicLibraryItemBinding
): RecyclerView.ViewHolder(binding.root) {
    private var listener: MusicLibraryClickListener? = null
    private var hideButtons: Boolean = false
    private var localMusicLibrary: MusicLibrary? = null

    init {
        binding.btnDetails.setOnClickListener {
            localMusicLibrary?.let {
                listener?.onDetailsClicked(it)
            }
        }

        binding.btnSongs.setOnClickListener {
            localMusicLibrary?.let {
                listener?.onSongsClicked(it)
            }
        }
    }

    fun bind(item: MusicLibrary) {
        localMusicLibrary = item
        binding.item = item
        binding.hideButtons = hideButtons
        binding.executePendingBindings()
    }

    companion object {
        fun from(parent: ViewGroup, listener: MusicLibraryClickListener?, hideButtons: Boolean): MusicLibraryViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = RecyclerviewMusicLibraryItemBinding.inflate(layoutInflater, parent,false)
            val holder = MusicLibraryViewHolder(binding)
            holder.listener = listener
            holder.hideButtons = hideButtons
            return holder
        }
    }
}

class UserDiffUtilItemCallback: DiffUtil.ItemCallback<MusicLibrary>() {
    override fun areItemsTheSame(oldItem: MusicLibrary, newItem: MusicLibrary): Boolean = oldItem.musicLibraryId == newItem.musicLibraryId
    override fun areContentsTheSame(oldItem: MusicLibrary, newItem: MusicLibrary): Boolean = oldItem == newItem
}

interface MusicLibraryClickListener {
    fun onDetailsClicked(musicLibrary: MusicLibrary)
    fun onSongsClicked(musicLibrary: MusicLibrary)
}