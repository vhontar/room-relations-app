package com.easycoding.learningroomrelations.business.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Song(
    @PrimaryKey val songId: Int,
    val name: String,
    val signerName: String,
    val signerLastname: String,
    val description: String
) {
    override fun toString() = name
}