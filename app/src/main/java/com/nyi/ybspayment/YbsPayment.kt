package com.nyi.ybspayment

import android.app.Application
import android.content.Context
import android.util.Log
import com.nyi.ybspayment.db.AppDatabase
import com.nyi.ybspayment.db.AppDatabaseJava
import com.nyi.ybspayment.db.DBHelper
import com.nyi.ybspayment.db.model.TransactionModel
import com.nyi.ybspayment.db.model.UserModel
import com.nyi.ybspayment.utils.Constants

/**
 * Created by IN-3442 on 27-Jul-18.
 */
class YbsPayment : Application() {

    companion object {
        lateinit var context : Context
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
        //AppDatabaseJava.getInstance(context)

        insertDummyData()
    }
    fun insertDummyData(){
        var dBHelper : DBHelper
        dBHelper = DBHelper(this)
/*

        //dummy transaction
        dBHelper.insertTransaction(TransactionModel("0924", "21", "3N/123", "12/12/1996", 1))
        dBHelper.insertTransaction(TransactionModel("0924", "21", "3N/123", "12/12/1996", 1))
        dBHelper.insertTransaction(TransactionModel("0924", "21", "3N/123", "12/12/1996", 1))
        dBHelper.insertTransaction(TransactionModel("0924", "21", "3N/123", "12/12/1996", 1))
        dBHelper.insertTransaction(TransactionModel("0924", "21", "3N/123", "12/12/1996", 1))

        //dummy user
        dBHelper.insertUser(UserModel("Nyi123", "0925412999", 20000))
*/

        //retrieve dummy user
        var user = dBHelper.readAllUser()
        Log.i(Constants.LOG, user.id.toString() + " " + user.userID + " " + user.phNo + " " + user.availableAMount)

        //retrieve dummy transaction
        val transactionList : List<TransactionModel> = dBHelper.readAllTransaction()
        for(tran in transactionList){
            Log.i(Constants.LOG, tran.transactionID.toString() + tran.carNo)
        }

        //dBHelper.updateAvailAmount(user.userID, 30000)

        user = dBHelper.readAllUser()
        Log.i(Constants.LOG, "Afetr update " + user.id.toString() + " " + user.userID + " " + user.phNo + " " + user.availableAMount)

    }



}