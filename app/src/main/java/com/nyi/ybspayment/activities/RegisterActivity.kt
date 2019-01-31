package com.nyi.ybspayment.activities

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.EditText
import com.nyi.ybspayment.R

import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        //setSupportActionBar(toolbar)

        /*fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }*/


        val etPhno = findViewById<EditText>(R.id.et_phno)
        val btnNext = findViewById<Button>(R.id.btn_next)
        btnNext.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, CodeFillActivity::class.java);
            startActivity(intent)
            finish()
        })
    }

}
