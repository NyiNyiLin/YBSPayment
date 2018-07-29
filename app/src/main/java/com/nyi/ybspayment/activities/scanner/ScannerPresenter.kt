package com.nyi.ybspayment.activities.scanner

import android.util.Log
import com.google.gson.Gson
import com.nyi.ybspayment.YbsPayment
import com.nyi.ybspayment.db.DBHelper
import com.nyi.ybspayment.utils.Constants
import com.nyi.ybspayment.vo.ScanResult

class ScannerPresenter(val scannerView: ScannerContract.ScannerView, val dbHelper: DBHelper = DBHelper(YbsPayment.context)) : ScannerContract.ScannerPresenter {

    val gson = Gson()
    lateinit var scanResult : ScanResult

    override fun saveTransaction(message: String?) {
        Log.d(Constants.LOG, "ScannerPresenter " + message)
        val msg = """
        {
            "carNo" : "3N/123",
            "fee" : 300
        }
        """

        scanResult = gson.fromJson(msg, ScanResult::class.java)
        Log.d(Constants.LOG, "ScannerPresenter " + scanResult.fee)

        val user = dbHelper.readAllUser()
        val remainingAmount = user.availableAMount - scanResult.fee

        if(remainingAmount <= 0){

        }else{
            dbHelper.updateAvailAmount(user.userID, remainingAmount)
        }

    }
}