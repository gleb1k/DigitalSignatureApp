package ru.glebik.digitalsignatureapp.presentation.ui

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import ru.glebik.core.designsystem.theme.AppTheme
import ru.glebik.core.utils.base.FileConstants
import ru.glebik.digitalsignatureapp.presentation.viewmodel.MainEvent
import ru.glebik.digitalsignatureapp.presentation.viewmodel.MainScreenModel
import ru.glebik.digitalsignatureapp.presentation.viewmodel.MainViewState
import ru.glebik.digitalsignatureapp.presentation.viewmodel.model.AlertModel

object MainScreen : Screen {

    @Composable
    override fun Content() {
        MainScreen()
    }

    @Composable
    private fun MainScreen(
        viewModel: MainScreenModel = getScreenModel()
    ) {

        val state by viewModel.state.collectAsStateWithLifecycle()

        if (state.alert.isShow) {
            BaseAlertDialog(onConfirm = {
                viewModel.event(
                    MainEvent.OnAlert(
                        AlertModel(
                            false,
                            "",
                            AlertModel.AlertType.INFO
                        )
                    )
                )
            }, dialogTitle = "${state.alert.type}", dialogText = state.alert.message)
        }

        MainView(
            state = state,
            event = viewModel::event
        )
    }

    @Composable
    private fun MainView(
        state: MainViewState,
        event: (MainEvent) -> Unit,
    ) {

        //считывание файла
        val selectFileActivity =
            rememberLauncherForActivityResult(contract = ActivityResultContracts.OpenDocument()) {
                event(MainEvent.OnLoadDocument(it))
            }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    start = AppTheme.padding.horizontalLarge,
                    end = AppTheme.padding.horizontalLarge,
                    top = AppTheme.padding.verticalLarge
                ),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            Button(onClick = {
                selectFileActivity.launch(FileConstants.MIME_TYPES)
            }) {
                Text(text = "Выберите файл")
            }
            state.document?.let {
                Text(
                    "Выбранный файл: ${state.document.name}",
                    style = TextStyle(
                        fontWeight = FontWeight.Bold
                    )
                )
            }
            Spacer(modifier = Modifier.height(AppTheme.padding.verticalLarge))

            PasswordField(
                password = state.queryPassword,
                onPasswordChanged = { event(MainEvent.OnQueryPasswordChange(it)) }
            )

            Spacer(modifier = Modifier.height(AppTheme.padding.verticalMedium))

            Button(onClick = {
                event(MainEvent.OnSignDocument)
            }) {
                Text(text = "Подписать")
            }
            Spacer(modifier = Modifier.height(AppTheme.padding.verticalLarge))
            state.signResultModel?.let {
                Text(
                    text = "Подпись:  ${state.signResultModel.signature}",
                    maxLines = 4,
                    overflow = TextOverflow.Ellipsis,
                )
                Spacer(modifier = Modifier.height(AppTheme.padding.verticalSmall))
                Text(
                    text = "Публичный ключ:  ${state.signResultModel.certificate.publicKey}",
                    maxLines = 4,
                    overflow = TextOverflow.Ellipsis,
                )
                Spacer(modifier = Modifier.height(AppTheme.padding.verticalLarge))
                Button(onClick = {
                    event(MainEvent.OnSaveSignature)
                }) {
                    Text(text = "Сохранить на устройство")
                }
                Button(onClick = {
                    event(MainEvent.OnVerifyDocument)
                }) {
                    Text(text = "Проверить")
                }

            }
        }
    }
}



