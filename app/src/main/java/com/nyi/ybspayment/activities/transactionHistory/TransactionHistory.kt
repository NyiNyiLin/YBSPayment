package com.nyi.ybspayment.activities.transactionHistory

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.nyi.ybspayment.R
import com.nyi.ybspayment.YbsPayment
import com.nyi.ybspayment.adapters.TransactionHistoryAddapter
import com.nyi.ybspayment.db.DBHelper
import com.nyi.ybspayment.db.model.TransactionModel
import java.security.AccessController.getContext

class TransactionHistory : AppCompatActivity() {

    lateinit var rvList : RecyclerView

    lateinit var historyAdapter : TransactionHistoryAddapter

    lateinit var dbHelper: DBHelper

    var transactionList : MutableList<TransactionModel> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transaction_history)

        /*actionBar.setTitle("History")
        actionBar.setDisplayHomeAsUpEnabled(true)*/

        dbHelper = DBHelper(applicationContext)

        transactionList = dbHelper.readAllTransaction()

        rvList = findViewById<RecyclerView>(R.id.rv_transaction_history)
        historyAdapter = TransactionHistoryAddapter(transactionList)
        rvList.setLayoutManager(LinearLayoutManager(applicationContext))
        rvList.setAdapter(historyAdapter)

    }
}
