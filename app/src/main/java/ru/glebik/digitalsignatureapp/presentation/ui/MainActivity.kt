package ru.glebik.digitalsignatureapp.presentation.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import cafe.adriel.voyager.navigator.Navigator
import ru.glebik.core.designsystem.settings.LocalSettingsEventBus
import ru.glebik.core.designsystem.settings.SettingsEventBus
import ru.glebik.core.designsystem.theme.AppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            val settingsEventBus = remember { SettingsEventBus() }
            val currentSettings = settingsEventBus.currentSettings.collectAsState().value

            AppTheme {
                CompositionLocalProvider(
                    LocalSettingsEventBus provides settingsEventBus
                ) {
                    Navigator(MainScreen)
                }
            }
        }

    }
}
