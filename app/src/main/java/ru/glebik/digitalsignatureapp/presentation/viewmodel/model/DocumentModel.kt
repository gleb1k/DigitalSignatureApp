package ru.glebik.digitalsignatureapp.presentation.viewmodel.model

import android.net.Uri

data class DocumentModel(
    val uri: Uri,
    val name: String,
    val byteArray: ByteArray,

    ) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as DocumentModel

        if (uri != other.uri) return false
        if (name != other.name) return false
        return byteArray.contentEquals(other.byteArray)
    }

    override fun hashCode(): Int {
        var result = uri.hashCode()
        result = 31 * result + name.hashCode()
        result = 31 * result + byteArray.contentHashCode()
        return result
    }
}
