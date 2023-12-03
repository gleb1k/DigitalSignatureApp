package ru.glebik.digitalsignatureapp.presentation.viewmodel.model

data class AlertModel(
    val isShow: Boolean = false,
    val message: String = "",
    val type: AlertType = AlertType.ERROR
) {
    enum class AlertType {
        ERROR, INFO,
    }
}
