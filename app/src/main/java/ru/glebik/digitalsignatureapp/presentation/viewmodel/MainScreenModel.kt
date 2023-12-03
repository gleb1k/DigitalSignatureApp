package ru.glebik.digitalsignatureapp.presentation.viewmodel

import android.net.Uri
import androidx.compose.runtime.Immutable
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.glebik.core.utils.signature.SignatureResultModel
import ru.glebik.digitalsignatureapp.domain.MainRepository
import ru.glebik.digitalsignatureapp.presentation.mvi.ViewAction
import ru.glebik.digitalsignatureapp.presentation.mvi.ViewEvent
import ru.glebik.digitalsignatureapp.presentation.mvi.ViewState
import ru.glebik.digitalsignatureapp.presentation.viewmodel.model.AlertModel
import ru.glebik.digitalsignatureapp.presentation.viewmodel.model.DocumentModel


@Immutable
data class MainViewState(
    val queryPassword: String = "",
    val document: DocumentModel? = null,

    val signResultModel: SignatureResultModel? = null,

    val alert: AlertModel = AlertModel(),
) : ViewState

sealed interface MainEvent : ViewEvent {
    data class OnQueryPasswordChange(val query: String) : MainEvent
    data class OnLoadDocument(val uri: Uri?) : MainEvent
    data object OnSignDocument : MainEvent

    data object OnSaveSignature : MainEvent
    data object OnVerifyDocument : MainEvent

    data class OnAlert(val alert: AlertModel) : MainEvent
}

sealed interface MainAction : ViewAction

class MainScreenModel(
    private val repository: MainRepository,
) : ScreenModel {

    private val _state = MutableStateFlow<MainViewState>(MainViewState())
    val state: StateFlow<MainViewState>
        get() = _state.asStateFlow()

    private val _action = MutableSharedFlow<MainAction?>()
    val action: SharedFlow<MainAction?>
        get() = _action.asSharedFlow()

    fun event(event: MainEvent) {
        when (event) {
            is MainEvent.OnSignDocument -> onSign()
            is MainEvent.OnQueryPasswordChange -> onQueryPasswordChange(event)

            is MainEvent.OnAlert -> onAlert(event)
            is MainEvent.OnLoadDocument -> onLoad(event)
            MainEvent.OnSaveSignature -> onSave()
            MainEvent.OnVerifyDocument -> onVerify()
        }
    }

    private fun onLoad(event: MainEvent.OnLoadDocument) {
        if (event.uri == null) {
            onAlert(MainEvent.OnAlert(AlertModel(true, "Введите данные")))
            return
        }
        screenModelScope.launch(Dispatchers.IO) {
            val uri = event.uri

            _state.emit(
                _state.value.copy(
                    document = repository.getDocumentModel(uri)
                )
            )
        }
    }

    private fun onSave() {

        if (state.value.signResultModel == null) {
            onAlert(MainEvent.OnAlert(AlertModel(true, "Нечего сохранять")))
            return
        }

        val signResultModel = state.value.signResultModel as SignatureResultModel

        screenModelScope.launch(Dispatchers.IO) {
            repository.saveSignatureAndPublicKey(signResultModel)
            onAlert(
                MainEvent.OnAlert(
                    AlertModel(
                        true,
                        "Сохранение подписи и публичного ключа завершенно успешно. Проверьте директорию Downloads",
                        AlertModel.AlertType.INFO
                    )
                )
            )
        }
    }

    private fun onQueryPasswordChange(event: MainEvent.OnQueryPasswordChange) {
        screenModelScope.launch {
            _state.emit(
                _state.value.copy(
                    queryPassword = event.query
                )
            )
        }
    }

    private fun onVerify() {
        if (state.value.signResultModel == null || state.value.document == null) {
            onAlert(MainEvent.OnAlert(AlertModel(true, "Подпись и сертификат не инициализированы")))
            return
        }

        screenModelScope.launch {
            val document = state.value.document as DocumentModel
            val signResultModel = state.value.signResultModel as SignatureResultModel

            val isVerify = repository.verifySignature(document.byteArray, signResultModel)

            if (isVerify)
                onAlert(
                    MainEvent.OnAlert(
                        AlertModel(
                            true,
                            "Документ подписан этой подписью и этим ключем",
                            AlertModel.AlertType.INFO
                        )

                    )
                )
            else
                onAlert(
                    MainEvent.OnAlert(
                        AlertModel(
                            true,
                            "Документ не подписан!",
                            AlertModel.AlertType.INFO
                        )

                    )
                )
        }

    }


    private fun onSign() {

        if (state.value.queryPassword.isBlank() || state.value.document == null) {
            onAlert(MainEvent.OnAlert(AlertModel(true, "Введите данные")))
            return
        }
        screenModelScope.launch {
            try {
                val document = state.value.document as DocumentModel
                val signResultModel = repository.signDocument(
                    document.byteArray,
                    state.value.queryPassword
                )
                _state.emit(
                    _state.value.copy(
                        signResultModel = signResultModel,
                        alert = AlertModel(
                            isShow = true,
                            message = "Подписание документа завершено успешно",
                            AlertModel.AlertType.INFO
                        )
                    )
                )

            } catch (throwable: Throwable) {
                onAlert(
                    MainEvent.OnAlert(
                        AlertModel(
                            true,
                            "Ошибка подписания документа",
                            AlertModel.AlertType.ERROR
                        )
                    )
                )
            }
        }
    }

    private fun onAlert(event: MainEvent.OnAlert) {
        screenModelScope.launch {
            _state.emit(
                _state.value.copy(
                    alert = event.alert
                )
            )
        }
    }
}


