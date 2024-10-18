package com.example.firstapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.firstapp.data.api.RetrofitInstance
import com.example.firstapp.data.model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException

class UserViewModel : ViewModel() {
    fun fetchUsers(onSuccess: (List<User>) -> Unit, onError: (Throwable) -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                // Directly call the suspend function
                val users = RetrofitInstance.api.getUsers()  // Ensure this is a suspend function
                // Switch to the main thread to call onSuccess
                withContext(Dispatchers.Main) {
                    onSuccess(users)
                }
            } catch (e: Exception) {
                // Handle specific exceptions like HttpException if necessary
                withContext(Dispatchers.Main) {
                    onError(e)
                }
            }
        }
    }
}


