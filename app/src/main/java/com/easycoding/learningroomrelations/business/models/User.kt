package com.easycoding.learningroomrelations.business.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey val userId: Int,
    val firstname: String,
    val lastname: String,
    val email: String
) {
    override fun toString() = "$firstname $lastname"
}