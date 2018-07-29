package com.nyi.ybspayment.activities.scanner

interface ScannerContract{
    public interface ScannerPresenter{
        fun saveTransaction(message : String?)
    }

    public interface ScannerView{

    }

}