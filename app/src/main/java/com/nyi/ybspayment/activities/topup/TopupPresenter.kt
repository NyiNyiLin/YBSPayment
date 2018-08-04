package com.nyi.ybspayment.activities.topup

import android.app.Activity
import android.content.Intent
import android.util.Log
import com.google.gson.Gson
import com.nyi.ybspayment.YbsPayment
import com.nyi.ybspayment.db.DBHelper
import com.nyi.ybspayment.utils.Constants
import com.nyi.ybspayment.vo.ScanResult
import com.nyi.ybspayment.vo.TopupScanResult

class TopupPresenter(val topupView: TopupContract.TopupView, val dbHelper: DBHelper = DBHelper(YbsPayment.context)) : TopupContract.TopupPresenter{
    val gson = Gson()
    lateinit var topupScanResult: TopupScanResult

    override fun setScannerResult(result: String) {
        Log.d(Constants.LOG, "TopupPresenter " + result)
        val msg = """
        {
            "rechargeAmount" : 1000
        }
        """

        //TODO
        //replace with actual scan result
        topupScanResult = gson.fromJson(msg, TopupScanResult::class.java)
        Log.d(Constants.LOG, "TopupPresenter balance " + topupScanResult.rechargeAmount)

        val user = dbHelper.readAllUser()
        val newAmount = user.availableAMount + topupScanResult.rechargeAmount

        dbHelper.updateAvailAmount(user.userID, newAmount)

        val intent = Intent()
        intent.putExtra(Constants.argTopupAMount, topupScanResult.rechargeAmount)

        topupView.finish(Activity.RESULT_OK, intent);
    }
}