package com.nyi.ybspayment.activities.scanner

import android.app.Activity
import android.content.Intent
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
            "carNo" : "3N/9898",
            "fee" : 200,
            "busLine" : "37"
        }
        """

        //TODO
        //replace with actual scan result
        scanResult = gson.fromJson(msg, ScanResult::class.java)
        Log.d(Constants.LOG, "ScannerPresenter " + scanResult.fee)

        val user = dbHelper.readAllUser()
        val remainingAmount = user.availableAMount - scanResult.fee

        if(remainingAmount <= 0){

            val intent = Intent()
            intent.putExtra(Constants.argFee, scanResult.fee)

            scannerView.finish(Activity.RESULT_CANCELED, intent);

        }else{
            dbHelper.updateAvailAmount(user.userID, remainingAmount)

            val intent = Intent()
            intent.putExtra(Constants.argFee, scanResult.fee)
            intent.putExtra(Constants.argbusLine, scanResult.busLine)
            intent.putExtra(Constants.argbusNo, scanResult.carNo)

            scannerView.finish(Activity.RESULT_OK, intent);
        }

    }

    override fun init() {
        val user = dbHelper.readAllUser()
        scannerView.updateAvailBalance(user.availableAMount)

    }
}