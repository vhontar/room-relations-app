package com.easycoding.learningroomrelations.presentation.songs.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.easycoding.learningroomrelations.databinding.RecyclerviewSongItemBinding
import com.easycoding.learningroomrelations.business.models.Song

class SongsAdapter(
    private val listener: SongClickListener? = null
): ListAdapter<Song, SongViewHolder>(UserDiffUtilItemCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongViewHolder =
        SongViewHolder.from(parent, listener)
    override fun onBindViewHolder(holder: SongViewHolder, position: Int) = holder.bind(getItem(position))
}

class SongViewHolder private constructor(
    private val binding: RecyclerviewSongItemBinding
): RecyclerView.ViewHolder(binding.root) {
    private var listener: SongClickListener? = null
    private var localSong: Song? = null

    init {
        binding.clSongRoot.setOnClickListener {
            localSong?.let {
                listener?.onSongClicked(it)
            }
        }
    }

    fun bind(item: Song) {
        localSong = item
        binding.item = item
        binding.executePendingBindings()
    }

    companion object {
        fun from(parent: ViewGroup, listener: SongClickListener?): SongViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = RecyclerviewSongItemBinding.inflate(layoutInflater, parent,false)
            val holder = SongViewHolder(binding)
            holder.listener = listener
            return holder
        }
    }
}

class UserDiffUtilItemCallback: DiffUtil.ItemCallback<Song>() {
    override fun areItemsTheSame(oldItem: Song, newItem: Song): Boolean = oldItem.songId == newItem.songId
    override fun areContentsTheSame(oldItem: Song, newItem: Song): Boolean = oldItem == newItem
}

interface SongClickListener {
    fun onSongClicked(song: Song)
}