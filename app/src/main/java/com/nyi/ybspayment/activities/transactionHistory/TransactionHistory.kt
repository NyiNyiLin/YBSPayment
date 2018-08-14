package com.nyi.ybspayment.activities.transactionHistory

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.FrameLayout
import com.nyi.ybspayment.R
import com.nyi.ybspayment.YbsPayment
import com.nyi.ybspayment.adapters.TransactionHistoryAddapter
import com.nyi.ybspayment.db.DBHelper
import com.nyi.ybspayment.db.model.TransactionModel
import com.nyi.ybspayment.fragments.TransactionHistoryFragment
import java.security.AccessController.getContext
import android.webkit.WebViewFragment
import android.widget.TextView
import com.nyi.ybspayment.fragments.TopupHistoryFragment


class TransactionHistory : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transaction_history)

        val tvTransaction = findViewById<TextView>(R.id.tv_control_transaction)
        val tvTopup = findViewById<TextView>(R.id.tv_control_topup)

        /*actionBar.setTitle("History")
        actionBar.setDisplayHomeAsUpEnabled(true)*/

        var transactionHistoryFragment : Fragment = TransactionHistoryFragment()
        var topupHistoryFragment : Fragment = TopupHistoryFragment()
        supportFragmentManager.beginTransaction().replace(R.id.frame_layout, transactionHistoryFragment)
                .commit()


        tvTransaction.setOnClickListener(View.OnClickListener {

            tvTransaction.setBackground(getDrawable(R.drawable.bg_history_transaction_press))
            tvTopup.setBackground(getDrawable(R.drawable.bg_history_topup_unpress))

            tvTransaction.setTextColor(resources.getColor(R.color.colorWhite))
            tvTopup.setTextColor(resources.getColor(R.color.secondaryColor))

            supportFragmentManager.beginTransaction().replace(R.id.frame_layout, transactionHistoryFragment)
                    .commit()
        })

        tvTopup.setOnClickListener(View.OnClickListener {

            tvTransaction.setBackground(getDrawable(R.drawable.bg_history_transaction_unpress))
            tvTopup.setBackground(getDrawable(R.drawable.bg_history_topup_press))

            tvTopup.setTextColor(resources.getColor(R.color.colorWhite))
            tvTransaction.setTextColor(resources.getColor(R.color.secondaryColor))

            supportFragmentManager.beginTransaction().replace(R.id.frame_layout, topupHistoryFragment)
                    .commit()
        })
    }
}
