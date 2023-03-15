package com.japparide.japparidedriver.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.japparide.japparidedriver.repositories.BaseRepository
import com.japparide.japparidedriver.repositories.auth_repo.AuthenticationRepository
import com.japparide.japparidedriver.ui.auth.AuthViewModel
import com.japparide.japparidedriver.ui.home.HomeViewModel
import java.lang.IllegalArgumentException

class ViewModelFactory(
    private val repository: BaseRepository
): ViewModelProvider.NewInstanceFactory(){

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return  return when {

            modelClass.isAssignableFrom(AuthViewModel::class.java) -> AuthViewModel(repository as AuthenticationRepository) as T
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> HomeViewModel(repository as AuthenticationRepository) as T



            else -> throw IllegalArgumentException("ViewModelClass Not Fount")
        }
    }
}