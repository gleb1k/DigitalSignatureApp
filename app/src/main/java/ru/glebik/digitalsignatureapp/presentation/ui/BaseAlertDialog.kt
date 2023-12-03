package ru.glebik.digitalsignatureapp.presentation.ui

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun BaseAlertDialog(
    onConfirm: () -> Unit,
    dialogTitle: String,
    dialogText: String,
) {

    AlertDialog(
        title = {
            Text(text = dialogTitle)
        },
        text = {
            Text(text = dialogText)
        },
        onDismissRequest = {
            onConfirm()
        },
        confirmButton = {
            Button(
                onClick = {
                    onConfirm()
                }
            ) {
                Text("Ок")
            }
        },
    )
}


