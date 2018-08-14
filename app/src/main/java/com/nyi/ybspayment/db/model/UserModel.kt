package com.nyi.ybspayment.db.model

import android.content.ContentValues
import android.database.Cursor
import com.nyi.ybspayment.db.DBContract
import com.nyi.ybspayment.utils.TimeUtil

/**
 * Created by IN-3442 on 28-Jul-18.
 */
class UserModel(val userID : String = "", val phNo : String = "", val availableAMount : Int = 0){

    var id : Int ? = null
    var createdDate : String? = null

    constructor(id : Int, userID: String, phNo: String, availableAMount: Int, createdDate : String) : this(userID, phNo, availableAMount){
        this.id = id
        this.createdDate = createdDate
    }

    companion object {
        fun cursorToModel(cursor: Cursor): UserModel {
            val id : Int
            val userID: String
            val phNo: String
            val availAmount: Int
            val createdDate : String

            id = cursor.getInt(cursor.getColumnIndex(DBContract.UserEntry.COLUMN_ID))
            userID = cursor.getString(cursor.getColumnIndex(DBContract.UserEntry.COLUMN_USER_ID))
            phNo = cursor.getString(cursor.getColumnIndex(DBContract.UserEntry.COLUMN_PHONE_NO))
            availAmount = cursor.getInt(cursor.getColumnIndex(DBContract.UserEntry.COLUMN_AVAIL_AMOUNT))
            createdDate = cursor.getString(cursor.getColumnIndex(DBContract.UserEntry.COLUMN_CREATED_DATE))

            return UserModel(id, userID, phNo, availAmount, createdDate)
        }

        fun modelToContentValue(user : UserModel) : ContentValues{

            val values = ContentValues()
            values.put(DBContract.UserEntry.COLUMN_USER_ID, user.userID)
            values.put(DBContract.UserEntry.COLUMN_PHONE_NO, user.phNo)
            values.put(DBContract.UserEntry.COLUMN_AVAIL_AMOUNT, user.availableAMount)
            values.put(DBContract.UserEntry.COLUMN_CREATED_DATE, TimeUtil.getCureenTime())

            return values
        }

    }
}