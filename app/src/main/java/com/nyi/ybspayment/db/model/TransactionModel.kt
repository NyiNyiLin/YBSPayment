package com.nyi.ybspayment.db.model

import android.content.ContentValues
import android.database.Cursor
import com.nyi.ybspayment.db.DBContract

/**
 * Created by IN-3442 on 27-Jul-18.
 */
class TransactionModel(val phNo : String, val busLine : String, val carNo : String, val time : String, val fee : Int, val isUploaded : Int) {
    var transactionID : Int? = null

    constructor(transactionID : Int, phNo : String, busLine : String, carNo : String, time : String, fee : Int, isUploaded : Int) : this (phNo, busLine, carNo, time, fee, isUploaded){
        this.transactionID = transactionID
    }

    companion object {
        fun cursorToModel(cursor : Cursor) : TransactionModel {
            val transactionID : Int
            val phNo : String
            val carNo : String
            val busLine : String
            val time : String
            val fee : Int
            val isUploaded : Int

            transactionID = cursor.getInt(cursor.getColumnIndex(DBContract.TransactionEntry.COLUMN_TRANSACTION_ID))
            phNo = cursor.getString(cursor.getColumnIndex(DBContract.TransactionEntry.COLUMN_PHONE_No))
            carNo = cursor.getString(cursor.getColumnIndex(DBContract.TransactionEntry.COLUMN_CAR_NO))
            busLine = cursor.getString(cursor.getColumnIndex(DBContract.TransactionEntry.COLUMN_BUS_LINE))
            time = cursor.getString(cursor.getColumnIndex(DBContract.TransactionEntry.COLUMN_TIME))
            fee = cursor.getInt(cursor.getColumnIndex(DBContract.TransactionEntry.COLUMN_FEE))
            isUploaded = cursor.getInt(cursor.getColumnIndex(DBContract.TransactionEntry.COLUMN_IS_UPLOADED))

            return TransactionModel(transactionID, phNo, busLine, carNo, time, fee, isUploaded)
        }

        fun modelToContentValue(transaction : TransactionModel) : ContentValues{
            // Create a new map of values, where column names are the keys
            val values = ContentValues()
            //TransactionModel.cvToModel(values)
            values.put(DBContract.TransactionEntry.COLUMN_PHONE_No, transaction.phNo)
            values.put(DBContract.TransactionEntry.COLUMN_BUS_LINE, transaction.busLine)
            values.put(DBContract.TransactionEntry.COLUMN_CAR_NO, transaction.carNo)
            values.put(DBContract.TransactionEntry.COLUMN_TIME, transaction.time)
            values.put(DBContract.TransactionEntry.COLUMN_FEE, transaction.fee)
            values.put(DBContract.TransactionEntry.COLUMN_IS_UPLOADED, transaction.isUploaded)

            return values
        }
    }


}