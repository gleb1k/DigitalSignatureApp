package ru.glebik.core.designsystem.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import ru.glebik.core.designsystem.theme.values.Colors
import ru.glebik.core.designsystem.theme.values.CornerShape
import ru.glebik.core.designsystem.theme.values.Padding
import ru.glebik.core.designsystem.theme.values.Typography
import ru.glebik.core.designsystem.theme.values.baseCornerShape
import ru.glebik.core.designsystem.theme.values.baseDarkPalette
import ru.glebik.core.designsystem.theme.values.baseDarkTypography
import ru.glebik.core.designsystem.theme.values.baseLightPalette
import ru.glebik.core.designsystem.theme.values.baseLightTypography
import ru.glebik.core.designsystem.theme.values.basePadding


@Composable
fun AppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors : Colors = when {
        darkTheme -> baseDarkPalette
        else -> baseLightPalette
    }

    val typography : Typography = when {
        darkTheme -> baseDarkTypography
        else ->  baseLightTypography
    }

    val padding : Padding = basePadding

    val cornerShape : CornerShape = baseCornerShape

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colors.background.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
        }
    }

    CompositionLocalProvider(
        LocalCustomColors provides colors,
        LocalCustomTypography provides typography,
        LocalCustomPadding provides padding,
        LocalCustomCornerShape provides cornerShape,
        content = content
    )
}