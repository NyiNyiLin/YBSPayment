package com.nyi.ybspayment.utils

import com.pvryan.easycrypt.ECKeys
import com.pvryan.easycrypt.ECResultListener
import com.pvryan.easycrypt.asymmetric.ECAsymmetric
import com.pvryan.easycrypt.asymmetric.ECRSAKeyPairListener
import com.pvryan.easycrypt.extensions.toBase64String
import java.security.KeyPair
import java.security.interfaces.RSAPrivateKey
import java.security.interfaces.RSAPublicKey

class RSAEncryUtil{

    companion object {
        lateinit var publicKey : RSAPublicKey
        lateinit var privateKey: RSAPrivateKey

        fun generatePrivatePublicKeyPair() {
            val eCryptAsymmetric = ECAsymmetric()
            val eCryptKeys = ECKeys()

            // Generate RSA KeyPair
            eCryptKeys.genRSAKeyPair(object : ECRSAKeyPairListener {

                override fun onGenerated(keyPair: KeyPair) {

                    publicKey = keyPair.public as RSAPublicKey
                    val publicKeyString = publicKey.encoded.toBase64String()

                    privateKey = keyPair.private as RSAPrivateKey
                    val privateKeyString = privateKey.encoded.toBase64String()


                }

                override fun onFailure(message: String, e: Exception) {

                }
            })
        }

        fun encryptWithpublicKey(text : String) : String{
            val eCryptAsymmetric = ECAsymmetric()
            eCryptAsymmetric.encrypt(text, publicKey, object : ECResultListener {
                override fun <T> onSuccess(result: T) {

                    when(result){
                        is String -> result
                        else -> "Invalid output, try again"
                    }

                }
                override fun onFailure(message: String, e: Exception) {

                }
            })

            return ""
        }

        fun decrptWithprivateKey(encryptedText : String) : String{
            val eCryptAsymmetric = ECAsymmetric()
            eCryptAsymmetric.decrypt(encryptedText, privateKey, object : ECResultListener {
                override fun <T> onSuccess(result: T) {
                     when(result){
                            is String -> result
                            else -> " Invalid output, try again"
                        }

                }

                override fun onFailure(message: String, e: Exception) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }
            })

            return ""
        }
    }
}
