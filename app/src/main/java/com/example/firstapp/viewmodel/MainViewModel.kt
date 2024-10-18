package com.example.firstapp.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.firstapp.data.api.RetrofitInstance
import com.example.firstapp.data.model.Message
import com.example.firstapp.data.model.User
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val _users = mutableStateListOf<User>()
    val users: List<User> get() = _users

    private val _messages = mutableStateListOf<Message>()
    val messages: List<Message> get() = _messages

    init {
        fetchUsers()
        fetchMessages()
    }

    private fun fetchUsers() {
        viewModelScope.launch {
            val fetchedUsers = RetrofitInstance.api.getUsers()
            _users.addAll(fetchedUsers)
        }
    }

    private fun fetchMessages() {
        viewModelScope.launch {
            val fetchedMessages = RetrofitInstance.api.getMessages()
            _messages.addAll(fetchedMessages)
        }
    }

    fun sendMessage(content: String, userId: Int) {
        viewModelScope.launch {
            val newMessage = Message(id = 0, userId = userId, content = content) // ID will be assigned by API
            RetrofitInstance.api.postMessage(newMessage)
            fetchMessages() // Refresh messages
        }
    }
}