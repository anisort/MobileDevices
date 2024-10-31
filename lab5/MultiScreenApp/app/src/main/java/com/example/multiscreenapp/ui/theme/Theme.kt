package com.example.multiscreenapp.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val LightBackgroundColor = Color(0xFFFFFBFE)
private val DarkBackgroundColor = Color(0xFF121212)

private val LightTextColor = Color(0xFF000000)
private val DarkTextColor = Color(0xFFFFFFFF)

private val LightPrimaryColor = Color(0xFF3700B3)
private val DarkPrimaryColor = Color(0xFFBB86FC)

// Темна кольорова схема
private val DarkColorScheme = darkColorScheme(
    primary = DarkPrimaryColor,
    background = DarkBackgroundColor,
    onPrimary = DarkTextColor,
    onSecondary = DarkTextColor,
    onTertiary = DarkTextColor,
    onBackground = DarkTextColor,
    onSurface = DarkTextColor
)

private val LightColorScheme = lightColorScheme(
    primary = LightPrimaryColor,
    background = LightBackgroundColor,
    onPrimary = LightTextColor,
    onSecondary = LightTextColor,
    onTertiary = LightTextColor,
    onBackground = LightTextColor,
    onSurface = LightTextColor
)


@Composable
fun MultiScreenAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}