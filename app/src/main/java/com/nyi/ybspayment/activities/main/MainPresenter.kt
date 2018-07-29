package com.nyi.ybspayment.activities.main

import android.view.View
import com.nyi.ybspayment.YbsPayment
import com.nyi.ybspayment.db.DBHelper
import com.nyi.ybspayment.db.model.UserModel

class MainPresenter(val mainView : MainContract.MainView, val dbHelper: DBHelper = DBHelper(YbsPayment.context)) : MainContract.MainPresenter {

    override fun clickPay() {
        mainView.goScannerActivity()
    }

    override fun init() {
        val user : UserModel = dbHelper.readAllUser()
        mainView.showUserInfo(user)
    }

    override fun setUser(user: UserModel) {

    }
}