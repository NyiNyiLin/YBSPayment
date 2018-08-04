package com.nyi.ybspayment.activities.main

import android.content.Intent
import android.view.View
import com.nyi.ybspayment.db.model.UserModel

interface MainContract {
    public interface  MainPresenter{
        fun setUser(user : UserModel)
        fun init()
        fun clickPay()
        fun clickHistory()
        fun clickTopup()
        fun resultFromScannerActivity(resultCode : Int, data : Intent?)
        fun resultFromTopupActivity(resultCode : Int, data : Intent?)
    }

    public interface MainView{
        fun updateAvailAmount(availAmount : Int)
        fun showUserInfo(user : UserModel)
        fun goScannerActivity()
        fun goTransactionHistoryActivity()
        fun goTopUpActivity()
        fun notEnoughBalanceDiaView(fee : Int)
        fun successDiaView(fee : Int, busNo : String, busLine : String)
        fun topupSuccessDiaView(rechargeAmount : Int)
    }
}