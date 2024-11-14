package com.example.getapiapplication.uicomponents

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun Loader() {
    CircularProgressIndicator(
        color = Color.Gray
    )
}
