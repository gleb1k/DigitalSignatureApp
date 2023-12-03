package ru.glebik.digitalsignatureapp.presentation.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import ru.glebik.core.designsystem.theme.AppTheme

@Composable
fun PasswordField(password: String, onPasswordChanged: (String) -> Unit) {
    Column(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = AppTheme.padding.horizontalLarge)
    ) {
        Text(text = "Введите пароль ЭЦП")
        OutlinedTextField(
            value = password,
            onValueChange = { onPasswordChanged(it) },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            ),
            label = { Text(text = "Пароль") },
            modifier = Modifier.fillMaxWidth()
        )
    }
}
