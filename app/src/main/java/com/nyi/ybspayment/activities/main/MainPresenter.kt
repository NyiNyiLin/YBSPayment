package com.nyi.ybspayment.activities.main

import android.app.Activity
import android.content.Intent
import android.view.View
import com.nyi.ybspayment.YbsPayment
import com.nyi.ybspayment.db.DBHelper
import com.nyi.ybspayment.db.model.UserModel
import com.nyi.ybspayment.utils.Constants

class MainPresenter(val mainView : MainContract.MainView, val dbHelper: DBHelper = DBHelper(YbsPayment.context)) : MainContract.MainPresenter {

    override fun clickPay() {
        val user : UserModel = dbHelper.readAllUser()
        if(user.availableAMount < 200){
            mainView.notEnoughBalanceDiaView(user.availableAMount)
        }else {
            mainView.goScannerActivity()
        }
    }

    override fun init() {
        val user : UserModel = dbHelper.readAllUser()
        mainView.showUserInfo(user)
    }

    override fun setUser(user: UserModel) {

    }

    override fun resultFromScannerActivity(resultCode: Int, data : Intent?) {
        if(resultCode == Activity.RESULT_OK){
            init()
            if(data != null){
                mainView.successDiaView(data.getIntExtra(Constants.argFee, 0),
                        data.getStringExtra(Constants.argbusNo),
                        data.getStringExtra(Constants.argbusLine))
            }

        }else{
            if(data != null) {
                mainView.notEnoughBalanceDiaView(data.getIntExtra(Constants.argFee, 0))
            }
        }
    }

    override fun resultFromTopupActivity(resultCode: Int, data: Intent?) {
        if(resultCode == Activity.RESULT_OK){
            if(data != null) {
                mainView.topupSuccessDiaView(data.getIntExtra(Constants.argTopupAMount, 0))
            }
        }
    }

    override fun clickHistory() {
        mainView.goTransactionHistoryActivity()
    }

    override fun clickTopup() {
        mainView.goTopUpActivity()
    }
}