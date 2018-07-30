package com.nyi.ybspayment.activities.scanner

import android.content.Intent

interface ScannerContract{
    public interface ScannerPresenter{
        fun saveTransaction(message : String?)
    }

    public interface ScannerView{
        fun finish(resultCode : Int, intent : Intent)
    }

}