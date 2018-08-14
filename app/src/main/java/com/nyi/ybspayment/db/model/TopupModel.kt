package com.nyi.ybspayment.db.model

import android.content.ContentValues
import android.database.Cursor
import com.nyi.ybspayment.db.DBContract
import com.nyi.ybspayment.utils.TimeUtil

class TopupModel(val userID : String, val rechargeAmount : Int, val topupDate : String) {
    var topupID : Int? = null

    constructor(topupID : Int, userID : String, rechargeAmount : Int, topupDate : String) : this(userID, rechargeAmount, topupDate){
        this.topupID = topupID
    }

    companion object {
        fun cursorToModel(cursor : Cursor) : TopupModel {
            val topupID : Int
            val userID : String
            val rechargeAmount : Int
            val topupDate : String

            topupID = cursor.getInt(cursor.getColumnIndex(DBContract.TopupEntry.COLUMN_ID))
            userID = cursor.getString(cursor.getColumnIndex(DBContract.TopupEntry.COLUMN_USER_ID))
            rechargeAmount = cursor.getInt(cursor.getColumnIndex(DBContract.TopupEntry.COLUMN_FEE))
            topupDate = cursor.getString(cursor.getColumnIndex(DBContract.TopupEntry.COLUMN_CREATED_DATE))

            return TopupModel(topupID, userID, rechargeAmount, topupDate)
        }

        fun modelToContentValue(topup : TopupModel) : ContentValues {
            // Create a new map of values, where column names are the keys
            val values = ContentValues()
            //TransactionModel.cvToModel(values)
            values.put(DBContract.TopupEntry.COLUMN_USER_ID, topup.userID)
            values.put(DBContract.TopupEntry.COLUMN_FEE, topup.rechargeAmount)
            values.put(DBContract.TopupEntry.COLUMN_CREATED_DATE, topup.topupDate)

            return values
        }
    }
}