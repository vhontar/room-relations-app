package com.easycoding.learningroomrelations.presentation.users

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.easycoding.learningroomrelations.datasource.local.dao.UserDao
import com.easycoding.learningroomrelations.business.models.User
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UsersViewModel @Inject constructor(
    userDao: UserDao
): ViewModel() {
    val users: LiveData<List<User>> = userDao.getAllUsers().asLiveData()
}