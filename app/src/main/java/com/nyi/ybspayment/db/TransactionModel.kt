package com.nyi.ybspayment.db

import android.content.ContentValues
import android.database.Cursor

/**
 * Created by IN-3442 on 27-Jul-18.
 */
class TransactionModel(val phNo : String, val busLine : String, val carNo : String, val time : String, val isUploaded : Int) {
    var transactionID : Int? = null
    constructor(transactionID : Int, phNo : String, busLine : String, carNo : String, time : String, isUploaded : Int) : this (phNo, busLine, carNo, time, isUploaded){
        this.transactionID = transactionID
    }

    companion object {
        fun cursorToModel(cursor : Cursor) : TransactionModel{
            val transactionID : Int
            val phNo : String
            val carNo : String
            val busLine : String
            val time : String
            val isUploaded : Int

            transactionID = cursor.getInt(cursor.getColumnIndex(DBContract.TransactionEntry.COLUMN_TRANSACTION_ID))
            phNo = cursor.getString(cursor.getColumnIndex(DBContract.TransactionEntry.COLUMN_PHONE_No))
            carNo = cursor.getString(cursor.getColumnIndex(DBContract.TransactionEntry.COLUMN_CAR_NO))
            busLine = cursor.getString(cursor.getColumnIndex(DBContract.TransactionEntry.COLUMN_BUS_LINE))
            time = cursor.getString(cursor.getColumnIndex(DBContract.TransactionEntry.COLUMN_TIME))
            isUploaded = cursor.getInt(cursor.getColumnIndex(DBContract.TransactionEntry.COLUMN_IS_UPLOADED))

            return TransactionModel(transactionID, phNo, busLine, carNo, time, isUploaded)
        }
    }


}