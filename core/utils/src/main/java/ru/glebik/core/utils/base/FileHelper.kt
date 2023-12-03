package ru.glebik.core.utils.base

import android.content.Context
import android.net.Uri
import android.provider.OpenableColumns
import java.io.File
import java.io.FileOutputStream
import java.nio.file.Files

class FileHelper(
    private val context: Context
) {

    fun getFileNameFromUri(uri : Uri): String {
        val cursor = context.contentResolver.query(uri, null, null, null, null)
        if (cursor == null || !cursor.moveToFirst()) return ""

        val indexName = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME)
        val fileName = cursor.getString(indexName)
        cursor.close()

        return fileName
    }

    fun readBytesFromUri(uri : Uri): ByteArray =
        context.contentResolver.openInputStream(uri).use { it!!.buffered().readBytes() }

    fun saveStringToFile(filePath: String, content: String) {
        val file = File(filePath)
        file.parentFile?.mkdirs()
        FileOutputStream(file).run {
            write(content.toByteArray())
            close()
        }
    }

    fun saveByteArrayToFile(filePath: String, content: ByteArray) {
        val file = File(filePath)
        file.parentFile?.mkdirs()
        FileOutputStream(file).run {
            write(content)
            close()
        }
    }
}