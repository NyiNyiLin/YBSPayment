package com.nyi.ybspayment.activities.topup

import android.content.Intent

class TopupContract{
    public interface TopupPresenter{
        fun setScannerResult(result : String)
    }

    public interface TopupView{
        fun finish(resultCode: Int, intent : Intent)
    }
}