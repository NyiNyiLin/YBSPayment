package com.nyi.ybspayment.activities

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.nyi.ybspayment.R
import com.nyi.ybspayment.YbsPayment
import com.nyi.ybspayment.activities.main.MainActivity
import com.nyi.ybspayment.network.api
import com.nyi.ybspayment.utils.Constants
import com.nyi.ybspayment.utils.RSAEncryUtil
import com.pvryan.easycrypt.ECKeys
import com.pvryan.easycrypt.ECResultListener
import com.pvryan.easycrypt.asymmetric.ECAsymmetric
import com.pvryan.easycrypt.asymmetric.ECRSAKeyPairListener
import com.pvryan.easycrypt.extensions.size
import com.pvryan.easycrypt.extensions.toBase64String

import kotlinx.android.synthetic.main.activity_code_fill.*
import org.jetbrains.anko.custom.onUiThread
import org.jetbrains.anko.find
import org.jetbrains.anko.runOnUiThread
import java.security.KeyPair
import java.security.interfaces.RSAPrivateKey
import java.security.interfaces.RSAPublicKey

class CodeFillActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_code_fill)
        //setSupportActionBar(toolbar)

        /*fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }*/



        val btnLogin = findViewById<Button>(R.id.btn_login)
        btnLogin.setOnClickListener(View.OnClickListener {

            RSAEncryUtil.generatePrivatePublicKeyPair()
            api.sendPublicKeyToServer()

            Constants.isLogin = Constants.LoginTrue
            val intent = Intent(this, MainActivity::class.java);
            //startActivity(intent)
            finish()
        })
    }

}
