package ru.glebik.core.utils.signature

import java.security.cert.Certificate

data class SignatureResultModel(
    val signature: String,
    val certificate: Certificate
)