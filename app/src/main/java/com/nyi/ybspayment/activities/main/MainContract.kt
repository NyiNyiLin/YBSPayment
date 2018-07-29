package com.nyi.ybspayment.activities.main

import android.view.View
import com.nyi.ybspayment.db.model.UserModel

interface MainContract {
    public interface  MainPresenter{
        fun setUser(user : UserModel)
        fun init()
        fun clickPay()
    }

    public interface MainView{
        fun updateAvailAmount(availAmount : Int)
        fun showUserInfo(user : UserModel)
        fun goScannerActivity()
    }
}