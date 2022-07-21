package com.itpeople.sample.mvvm.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.itpeople.sample.mvvm.data.model.ResultsItem
import com.itpeople.sample.mvvm.data.repository.UserRepository
import com.itpeople.sample.mvvm.utils.NetworkHelper
import com.itpeople.sample.mvvm.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val networkHelper: NetworkHelper,
    private val userRepository: UserRepository
) : ViewModel() {

    private val _results = MutableLiveData<Resource<List<ResultsItem?>>>()
    val users: LiveData<Resource<List<ResultsItem?>>>
        get() = _results

    init {
        initUsers()
    }

    private fun initUsers() {

        viewModelScope.launch {
            _results.postValue(Resource.loading(null))
            if (networkHelper.isNetworkConnected()) {
                userRepository.getUser().let {
                    if (it.isSuccessful) {
                        _results.postValue(Resource.success(it.body()?.results))
                    } else _results.postValue(Resource.error(it.errorBody().toString(), null))
                }
            } else _results.postValue(Resource.error("No internet connection", null))

        }
    }

    fun insertUsers(users: List<ResultsItem?>) = userRepository.insertUser(users)

    fun fetchUsers() = userRepository.fetchUsers()

}