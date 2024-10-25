package com.example.firstapp.ui

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.Composable
import com.example.firstapp.data.model.User
import com.example.firstapp.viewmodel.MainViewModel

@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun MainScreen(viewModel: MainViewModel = viewModel()) {
    var messageContent by remember { mutableStateOf("") }
    val users: List<User> = viewModel.users
    val messages = viewModel.messages

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Users", style = MaterialTheme.typography.h5)
        users.forEach { user ->
            Text(user.name)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text("Messages", style = MaterialTheme.typography.h5)
        messages.forEach { message ->
            Text("${message.content} (User ID: ${message.userId})")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            TextField(
                value = messageContent,
                onValueChange = { messageContent = it },
                label = { Text("Message") },
                modifier = Modifier.weight(1f)
            )
            Button(onClick = {
                if (messageContent.isNotEmpty()) {
                    viewModel.sendMessage(messageContent, 1) // Assuming userId 1 for demo
                    messageContent = ""
                }
            }) {
                Text("Send")
            }
        }

    }
}


