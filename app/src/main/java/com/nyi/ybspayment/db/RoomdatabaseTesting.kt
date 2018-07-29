package com.nyi.ybspayment.db

import android.os.Handler
import android.util.Log

/**
 * Created by IN-3442 on 28-Jul-18.
 */
/*
        Room database testing
     */
class RoomdatabaseTesting {


    private lateinit var mDbWorkerThread: DBWorkerThread
    private val mUiHandler = Handler()
    private var mDb: AppDatabaseJava? = null

    fun insertTransactionInDb(transaction: Transaction) {
        mDbWorkerThread = DBWorkerThread("dbWorkerThread")
        mDbWorkerThread.start()
        mDb = AppDatabaseJava.getInstance(null)

        val task = Runnable { mDb?.TransactionDao()?.insert(transaction) }
        mDbWorkerThread.postTask(task)
    }

    private fun fetchTransactionDataFromDb() {
        val task = Runnable {
            val transactionData = mDb?.TransactionDao()?.getAll()
            mUiHandler.post({
                if (transactionData == null || transactionData?.size == 0) {
                    for (tran in transactionData.orEmpty()) {
                        Log.i("Payment", tran.toString())
                    }
                } else {

                }
            })
        }
        mDbWorkerThread.postTask(task)
    }
}