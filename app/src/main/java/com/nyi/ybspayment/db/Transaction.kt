package com.nyi.ybspayment.db

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Created by IN-3442 on 23-Jul-18.
 */
@Entity(tableName = "Transaction")
data class Transaction(
    @PrimaryKey(autoGenerate = true)
    val id: Long?,
    @ColumnInfo(name = "ph_no") val phNo : String = "",
    @ColumnInfo(name = "bus_line") val busLine : String = "",
    @ColumnInfo(name = "car_no") val carNo : String = "",
    @ColumnInfo(name = "time") val time : String = "",
    @ColumnInfo(name = "fee")val fee : Int = 0
){


    constructor() : this(null, "", "", "") {
        // constructor body
    }

}