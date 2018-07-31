package com.nyi.ybspayment.activities.scanner

import android.content.Intent

interface ScannerContract{
    public interface ScannerPresenter{
        fun init()
        fun saveTransaction(message : String?)
    }

    public interface ScannerView{
        fun finish(resultCode : Int, intent : Intent)
        fun updateAvailBalance(avail : Int)
    }

}