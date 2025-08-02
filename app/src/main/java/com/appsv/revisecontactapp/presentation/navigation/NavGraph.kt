package com.appsv.revisecontactapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.appsv.revisecontactapp.presentation.ContactViewModel
import com.appsv.revisecontactapp.presentation.screen.AddEditScreen
import com.appsv.revisecontactapp.presentation.screen.HomeScreen

@Composable
fun NavGraph(navController: NavHostController,viewModel: ContactViewModel){

    val state by viewModel.state.collectAsState()
    NavHost(navController = navController, startDestination = Routes.Home.route){

        composable(Routes.AddEdit.route){
            AddEditScreen(navHostController = navController,
                state = viewModel.state.collectAsState().value,
                onEvent = { viewModel.saveContact()}
            )
        }

        composable(Routes.Home.route){
            HomeScreen(navController = navController,
                state = state,
                viewModel = viewModel
            )
        }


    }
}