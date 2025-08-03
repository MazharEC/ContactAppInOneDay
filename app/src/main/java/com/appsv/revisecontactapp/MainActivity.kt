package com.appsv.revisecontactapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.appsv.revisecontactapp.presentation.ContactViewModel
import com.appsv.revisecontactapp.presentation.navigation.NavGraph
import com.appsv.revisecontactapp.ui.theme.ReviseContactAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            val viewModel = hiltViewModel<ContactViewModel>()
            val navController = rememberNavController()

            ReviseContactAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                    NavGraph(navController = navController, viewModel = viewModel)
                }
            }
        }
    }
}

