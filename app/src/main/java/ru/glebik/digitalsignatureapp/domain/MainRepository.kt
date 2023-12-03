package ru.glebik.digitalsignatureapp.domain

import android.net.Uri
import ru.glebik.core.utils.signature.SignatureResultModel
import ru.glebik.digitalsignatureapp.presentation.viewmodel.model.DocumentModel

interface MainRepository {

    suspend fun signDocument(document: ByteArray, password: String): SignatureResultModel

    suspend fun saveSignatureAndPublicKey(signModel: SignatureResultModel)

    suspend fun verifySignature(document: ByteArray, signModel: SignatureResultModel): Boolean

    suspend fun getDocumentModel(uri: Uri): DocumentModel
}