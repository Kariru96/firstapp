package com.example.firstapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.firstapp.data.ui.MainScreen
import com.example.firstapp.viewmodel.MainViewModel


// MainActivity
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface {
                    // Get the MainViewModel and pass it to MainScreen
                    val mainViewModel: MainViewModel = viewModel()
                    MainScreen(viewModel = mainViewModel)
                }
            }
        }
    }
}