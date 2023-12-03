package ru.glebik.digitalsignatureapp.data

import android.net.Uri
import android.os.Environment
import ru.glebik.core.utils.base.FileHelper
import ru.glebik.core.utils.signature.DigitalSignatureHelper
import ru.glebik.core.utils.signature.SignatureResultModel
import ru.glebik.digitalsignatureapp.domain.MainRepository
import ru.glebik.digitalsignatureapp.presentation.viewmodel.model.DocumentModel

class MainRepositoryImpl(
    private val fileHelper: FileHelper
) : MainRepository {

    override suspend fun signDocument(document: ByteArray, password: String): SignatureResultModel =
        DigitalSignatureHelper.signData(document, password)


    override suspend fun saveSignatureAndPublicKey(signModel: SignatureResultModel) {
        fileHelper.saveByteArrayToFile(
            Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_DOWNLOADS
            ).path + "/signature.txt", signModel.signature.toByteArray()
        )
        fileHelper.saveByteArrayToFile(
            Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_DOWNLOADS
            ).path + "/publicKey.txt", signModel.certificate.publicKey.encoded
        )
    }

    override suspend fun verifySignature(
        document: ByteArray,
        signModel: SignatureResultModel
    ): Boolean =
        DigitalSignatureHelper.verifyData(document, signModel)

    override suspend fun getDocumentModel(uri: Uri): DocumentModel =
        DocumentModel(uri, fileHelper.getFileNameFromUri(uri), fileHelper.readBytesFromUri(uri))
}