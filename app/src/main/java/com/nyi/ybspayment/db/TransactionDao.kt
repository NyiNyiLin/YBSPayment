package com.nyi.ybspayment.db

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.arch.persistence.room.Query

/**
 * Created by IN-3442 on 23-Jul-18.
 */
@Dao
interface TransactionDao {

    @Query("SELECT * from Transaction")
    fun getAll(): List<Transaction>

    @Insert(onConflict = REPLACE)
    fun insert(transaction: Transaction)

    @Query("DELETE from Transaction")
    fun deleteAll()

}
