package com.easycoding.learningroomrelations.ui.users

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.easycoding.learningroomrelations.database.dao.UserDao
import com.easycoding.learningroomrelations.models.User
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UsersViewModel @Inject constructor(
    userDao: UserDao
): ViewModel() {
    val users: LiveData<List<User>> = userDao.getAllUsers().asLiveData()
}