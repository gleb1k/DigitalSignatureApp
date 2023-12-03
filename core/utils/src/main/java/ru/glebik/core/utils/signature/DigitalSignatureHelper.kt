package ru.glebik.core.utils.signature

import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import android.util.Base64
import android.util.Log
import ru.glebik.core.utils.signature.DigitalSignatureConstants.ANDROID_KEYSTORE
import ru.glebik.core.utils.signature.DigitalSignatureConstants.KEY_ALIAS
import java.math.BigInteger
import java.security.KeyPair
import java.security.KeyPairGenerator
import java.security.KeyStore
import java.security.PrivateKey
import java.security.PublicKey
import java.security.Signature
import java.security.cert.Certificate
import java.util.Calendar
import javax.security.auth.x500.X500Principal

object DigitalSignatureHelper {

    private fun generateKeyPair(): KeyPair {

        val startDate = Calendar.getInstance()
        val endDate = Calendar.getInstance()
        endDate.add(Calendar.YEAR, 1)


        val keyPairGenerator: KeyPairGenerator =
            KeyPairGenerator.getInstance(
                KeyProperties.KEY_ALGORITHM_RSA,
                DigitalSignatureConstants.ANDROID_KEYSTORE
            )
        val parameterSpec: KeyGenParameterSpec = KeyGenParameterSpec.Builder(
            DigitalSignatureConstants.KEY_ALIAS,
            KeyProperties.PURPOSE_SIGN or KeyProperties.PURPOSE_VERIFY
        ).run {
            setCertificateSerialNumber(BigInteger.valueOf(777))       //Serial number used for the self-signed certificate of the generated key pair, default is 1
            setCertificateSubject(X500Principal("CN=${DigitalSignatureConstants.KEY_ALIAS}"))     //Subject used for the self-signed certificate of the generated key pair, default is CN=fake
            setDigests(KeyProperties.DIGEST_SHA256)                         //Set of digests algorithms with which the key can be used
            setSignaturePaddings(KeyProperties.SIGNATURE_PADDING_RSA_PKCS1) //Set of padding schemes with which the key can be used when signing/verifying
            setCertificateNotBefore(startDate.time)                         //Start of the validity period for the self-signed certificate of the generated, default Jan 1 1970
            setCertificateNotAfter(endDate.time)                            //End of the validity period for the self-signed certificate of the generated key, default Jan 1 2048             //Sets whether this key is authorized to be used only if the user has been authenticated, default false //Duration(seconds) for which this key is authorized to be used after the user is successfully authenticated
            build()
        }
        keyPairGenerator.initialize(parameterSpec)

        val keyPair = keyPairGenerator.genKeyPair()
        Log.i("Key pair generated:", "$keyPair")
        return keyPair
    }

    fun signData(data: ByteArray, password: String): SignatureResultModel {

        generateKeyPair()
        val keyStore: KeyStore =
            KeyStore.getInstance(DigitalSignatureConstants.ANDROID_KEYSTORE).apply {
                load(null)
            }
        val privateKey: PrivateKey =
            keyStore.getKey(DigitalSignatureConstants.KEY_ALIAS, password.toCharArray()) as PrivateKey

        val signatureMakerInstance = Signature.getInstance("SHA256withRSA")
        signatureMakerInstance.initSign(privateKey)
        signatureMakerInstance.update(data)

        val signatureByteArray = signatureMakerInstance.sign() ?: throw RuntimeException()
        val signatureResult = Base64.encodeToString(signatureByteArray, Base64.DEFAULT)
        val certificate: Certificate = keyStore.getCertificate(KEY_ALIAS)

        Log.i("Signed Succesfully", signatureResult)
        return SignatureResultModel(signatureResult, certificate)
    }

    fun verifyData(data: ByteArray, signModel: SignatureResultModel): Boolean {

        val signature: ByteArray = Base64.decode(signModel.signature, Base64.DEFAULT)
        val publicKey: PublicKey = signModel.certificate.publicKey

        val isValid: Boolean = Signature.getInstance("SHA256withRSA").run {
            initVerify(publicKey)
            update(data)
            verify(signature)
        }
        Log.i("isVerified", "$isValid")
        return isValid
    }
}