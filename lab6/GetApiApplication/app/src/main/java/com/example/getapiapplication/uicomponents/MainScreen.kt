package com.example.getapiapplication.uicomponents

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.getapiapplication.viewmodel.UserViewModel

@Composable
fun MainScreen() {
    val userViewModel: UserViewModel = viewModel()

    Column(modifier = Modifier.padding(16.dp)) {
        UserListScreen(userViewModel = userViewModel)
    }
}