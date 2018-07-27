package com.nyi.ybspayment.db

import android.provider.BaseColumns

/**
 * Created by IN-3442 on 27-Jul-18.
 */
object DBContract {
    /* Inner class that defines the table contents */
    class TransactionEntry : BaseColumns {
        companion object {
            val TABLE_NAME = "transaction_table"
            val COLUMN_TRANSACTION_ID = "transaction_id"
            val COLUMN_PHONE_No = "phone_no"
            val COLUMN_BUS_LINE = "bus_line"
            val COLUMN_CAR_NO = "car_no"
            val COLUMN_TIME = "time"
            val COLUMN_IS_UPLOADED = "is_uploaded"
        }
    }
}