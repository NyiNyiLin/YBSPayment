package com.nyi.ybspayment.activities

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.nyi.ybspayment.R
import com.pvryan.easycrypt.ECKeys
import com.pvryan.easycrypt.ECResultListener
import com.pvryan.easycrypt.asymmetric.ECAsymmetric
import com.pvryan.easycrypt.asymmetric.ECRSAKeyPairListener
import com.pvryan.easycrypt.extensions.toBase64String

import kotlinx.android.synthetic.main.activity_encryption_test.*
import java.security.KeyPair
import java.security.interfaces.RSAPrivateKey
import java.security.interfaces.RSAPublicKey

class EncryptionTestActivity : AppCompatActivity() {

    lateinit var tvPrivate : TextView
    lateinit var  tvPublic : TextView
    lateinit var etTextToEncrypt : EditText
    lateinit var tvEncryptText : TextView
    lateinit var tvDecryotedText : TextView
    lateinit var btnEncrypt : Button
    lateinit var btnDecrypt : Button
    lateinit var btnGenKeyPair : Button

    var publicKeyString : String = ""
    var privateKeyString : String = ""
    lateinit var publicKey : RSAPublicKey
    lateinit var privateKey: RSAPrivateKey

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_encryption_test)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

        tvPrivate = findViewById(R.id.tv_privateKey)
        tvPublic = findViewById(R.id.tv_publicKey)
        etTextToEncrypt = findViewById(R.id.et_text_to_encrypt)
        tvEncryptText = findViewById(R.id.tv_encrypted_text)
        tvDecryotedText = findViewById(R.id.tv_decrypted_text)
        btnEncrypt = findViewById(R.id.btn_encrypt)
        btnDecrypt = findViewById(R.id.btn_decrypt)
        btnGenKeyPair = findViewById(R.id.btn_gen_keypair)

        btnEncrypt.setOnClickListener {
            encryptWithpublicKey()
        }

        btnGenKeyPair.setOnClickListener {
            generatePrivatePublicKeyPair()
        }

        btnDecrypt.setOnClickListener {
            decrptWithprivateKey()
        }
    }

    fun generatePrivatePublicKeyPair(){
        val eCryptAsymmetric = ECAsymmetric()
        val eCryptKeys = ECKeys()

        // Generate RSA KeyPair
        eCryptKeys.genRSAKeyPair(object : ECRSAKeyPairListener {

            override fun onGenerated(keyPair: KeyPair) {

                publicKey = keyPair.public as RSAPublicKey
                publicKeyString = publicKey.encoded.toBase64String()

                privateKey = keyPair.private as RSAPrivateKey
                privateKeyString = privateKey.encoded.toBase64String()

                runOnUiThread { tvPublic.text = publicKeyString.length.toString() + publicKeyString }

                // Symmetrically encrypt private key

                runOnUiThread { tvPrivate.text = privateKeyString.length.toString() + privateKeyString }

            }

            override fun onFailure(message: String, e: Exception) {

            }
        })
    }

    fun encryptWithpublicKey(){
        val eCryptAsymmetric = ECAsymmetric()
        eCryptAsymmetric.encrypt(etTextToEncrypt.text.toString(), publicKey, object : ECResultListener {
            override fun <T> onSuccess(result: T) {
                runOnUiThread{
                    tvEncryptText.text = when(result){
                        is String -> result
                        else -> "Invalid output, try again"
                    }
                }
            }
            override fun onFailure(message: String, e: Exception) {

            }
        })
    }

    fun decrptWithprivateKey(){
        val eCryptAsymmetric = ECAsymmetric()
        eCryptAsymmetric.decrypt(tvEncryptText.text.toString(), privateKey, object : ECResultListener{
            override fun <T> onSuccess(result: T) {
                runOnUiThread{
                    tvDecryotedText.text = when(result){
                        is String -> result
                        else -> " Invalid output, try again"
                    }
                }
            }

            override fun onFailure(message: String, e: Exception) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        })
    }
}
