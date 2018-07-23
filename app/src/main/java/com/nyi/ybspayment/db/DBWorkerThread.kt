package com.nyi.ybspayment.db

import android.os.Handler
import android.os.HandlerThread

/**
 * Created by IN-3442 on 23-Jul-18.
 */
class DBWorkerThread(threadName: String) : HandlerThread(threadName){

    private lateinit var mWorkerHandler: Handler

    override fun onLooperPrepared() {
        super.onLooperPrepared()
        mWorkerHandler = Handler(looper)
    }

    fun postTask(task: Runnable) {
        mWorkerHandler.post(task)
    }

}