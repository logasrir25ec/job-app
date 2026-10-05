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

private val DarkColorScheme =
  darkColorScheme(
    primary = TechBluePrimary,
    onPrimary = Color.White,
    primaryContainer = TechBlueDark,
    onPrimaryContainer = TechBlueContainer,
    secondary = CoreAmberSecondary,
    onSecondary = Color.White,
    secondaryContainer = Color(0xFF78350F),
    onSecondaryContainer = CoreAmberContainer,
    tertiary = SuccessEmerald,
    background = SlateBackgroundDark,
    surface = SlateSurfaceDark,
    surfaceVariant = SlateSurfaceVariantDark,
    onBackground = TextPrimaryDark,
    onSurface = TextPrimaryDark,
    onSurfaceVariant = TextSecondaryDark,
    outline = SlateBorderDark
  )

private val LightColorScheme =
  lightColorScheme(
    primary = TechBluePrimary,
    onPrimary = Color.White,
    primaryContainer = TechBlueContainer,
    onPrimaryContainer = OnTechBlueContainer,
    secondary = CoreAmberSecondary,
    onSecondary = Color.White,
    secondaryContainer = CoreAmberContainer,
    onSecondaryContainer = OnCoreAmberContainer,
    tertiary = SuccessEmerald,
    background = SlateBackgroundLight,
    surface = SlateSurfaceLight,
    surfaceVariant = SlateSurfaceVariantLight,
    onBackground = TextPrimaryLight,
    onSurface = TextPrimaryLight,
    onSurfaceVariant = TextSecondaryLight,
    outline = Color(0xFFCBD5E1)
  )

@Composable
fun MyApplicationTheme(
  darkTheme: Boolean = isSystemInDarkTheme(),
  dynamicColor: Boolean = false,
  content: @Composable () -> Unit,
) {
  val colorScheme =
    when {
      dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
        val context = LocalContext.current
        if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
      }

      darkTheme -> DarkColorScheme
      else -> LightColorScheme
    }

  MaterialTheme(colorScheme = colorScheme, typography = Typography, content = content)
}
