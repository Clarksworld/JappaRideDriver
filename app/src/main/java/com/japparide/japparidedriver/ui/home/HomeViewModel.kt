package com.japparide.japparidedriver.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.japparide.japparidedriver.repositories.auth_repo.AuthenticationRepository

class HomeViewModel(private val repository: AuthenticationRepository) : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text
}