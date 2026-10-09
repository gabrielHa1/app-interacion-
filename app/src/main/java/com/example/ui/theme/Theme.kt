package com.example.ui.theme

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

private val DarkColorScheme = darkColorScheme(
    primary = TechPrimary,
    onPrimary = Color(0xFF00363D),
    primaryContainer = Color(0xFF004F59),
    onPrimaryContainer = Color(0xFF9CF0FF),
    secondary = TechSecondary,
    onSecondary = Color(0xFF381E72),
    secondaryContainer = Color(0xFF4F378B),
    onSecondaryContainer = Color(0xFFEADDFF),
    tertiary = TechTertiary,
    onTertiary = Color(0xFF003824),
    background = TechDarkBackground,
    onBackground = Color(0xFFF1F5F9),
    surface = TechDarkSurface,
    onSurface = Color(0xFFF8FAFC),
    surfaceVariant = TechDarkSurfaceVariant,
    onSurfaceVariant = Color(0xFFCBD5E1),
    outline = TechDarkBorder,
    error = TechError
)

private val LightColorScheme = lightColorScheme(
    primary = Color(0xFF006877),
    onPrimary = Color.White,
    primaryContainer = Color(0xFFA5EEFF),
    onPrimaryContainer = Color(0xFF001F25),
    secondary = Color(0xFF703EB5),
    onSecondary = Color.White,
    secondaryContainer = Color(0xFFEDDCFF),
    onSecondaryContainer = Color(0xFF2B0053),
    tertiary = Color(0xFF006C47),
    onTertiary = Color.White,
    background = TechLightBackground,
    onBackground = Color(0xFF0F172A),
    surface = TechLightSurface,
    onSurface = Color(0xFF0F172A),
    surfaceVariant = TechLightSurfaceVariant,
    onSurfaceVariant = Color(0xFF475569),
    outline = TechLightBorder,
    error = TechError
)

@Composable
fun MyApplicationTheme(
    darkTheme: Boolean = true, // Default to sleek tech dark mode for authentic gaming store vibe
    dynamicColor: Boolean = false,
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
