package com.nyi.ybspayment

import android.app.Application
import android.content.Context
import com.nyi.ybspayment.db.AppDatabase
import com.nyi.ybspayment.db.AppDatabaseJava

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

    }



}