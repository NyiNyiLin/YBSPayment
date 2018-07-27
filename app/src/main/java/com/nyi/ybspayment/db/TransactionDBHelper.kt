package com.nyi.ybspayment.db

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteConstraintException
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper

/**
 * Created by IN-3442 on 27-Jul-18.
 */
class TransactionDBHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(SQL_CREATE_ENTRIES)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES)
        onCreate(db)
    }

    override fun onDowngrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        onUpgrade(db, oldVersion, newVersion)
    }

    @Throws(SQLiteConstraintException::class)
    fun insertUser(transaction: TransactionModel): Boolean {
        // Gets the data repository in write mode
        val db = writableDatabase

        // Create a new map of values, where column names are the keys
        val values = ContentValues()
        //TransactionModel.cvToModel(values)
        values.put(DBContract.TransactionEntry.COLUMN_PHONE_No, transaction.phNo)
        values.put(DBContract.TransactionEntry.COLUMN_BUS_LINE, transaction.busLine)
        values.put(DBContract.TransactionEntry.COLUMN_CAR_NO, transaction.carNo)
        values.put(DBContract.TransactionEntry.COLUMN_TIME, transaction.time)
        values.put(DBContract.TransactionEntry.COLUMN_IS_UPLOADED, transaction.isUploaded)

        // Insert the new row, returning the primary key value of the new row
        val newRowId = db.insert(DBContract.TransactionEntry.TABLE_NAME, null, values)

        return true
    }

    @Throws(SQLiteConstraintException::class)
    fun deleteUser(transactionId: String): Boolean {
        // Gets the data repository in write mode
        val db = writableDatabase
        // Define 'where' part of query.
        val selection = DBContract.TransactionEntry.COLUMN_TRANSACTION_ID + " LIKE ?"
        // Specify arguments in placeholder order.
        val selectionArgs = arrayOf(transactionId)
        // Issue SQL statement.
        db.delete(DBContract.TransactionEntry.TABLE_NAME, selection, selectionArgs)

        return true
    }

    fun readTransaction(transactionID: String): ArrayList<TransactionModel> {
        val transactionList = ArrayList<TransactionModel>()
        val db = writableDatabase
        var cursor: Cursor? = null
        try {
            cursor = db.rawQuery("select * from " + DBContract.TransactionEntry.TABLE_NAME + " WHERE " + DBContract.TransactionEntry.COLUMN_TRANSACTION_ID + "='" + transactionID + "'", null)
        } catch (e: SQLiteException) {
            // if table not yet present, create it
            db.execSQL(SQL_CREATE_ENTRIES)
            return ArrayList()
        }

        if (cursor!!.moveToFirst()) {
            while (cursor.isAfterLast == false) {
                transactionList.add(TransactionModel.cursorToModel(cursor))
                cursor.moveToNext()
            }
        }
        return transactionList
    }

    fun readAllTransaction(): ArrayList<TransactionModel> {
        val transactionList = ArrayList<TransactionModel>()
        val db = writableDatabase
        var cursor: Cursor? = null
        try {
            cursor = db.rawQuery("select * from " + DBContract.TransactionEntry.TABLE_NAME, null)
        } catch (e: SQLiteException) {
            db.execSQL(SQL_CREATE_ENTRIES)
            return ArrayList()
        }

        if (cursor!!.moveToFirst()) {
            while (cursor.isAfterLast == false) {
                transactionList.add(TransactionModel.cursorToModel(cursor))
                cursor.moveToNext()
            }
        }
        return transactionList
    }

    companion object {
        // If you change the database schema, you must increment the database version.
        val DATABASE_VERSION = 1
        val DATABASE_NAME = "ybspayment.db"

        private val SQL_CREATE_ENTRIES =
                "CREATE TABLE " + DBContract.TransactionEntry.TABLE_NAME + " (" +
                        DBContract.TransactionEntry.COLUMN_TRANSACTION_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                        DBContract.TransactionEntry.COLUMN_PHONE_No + " TEXT," +
                        DBContract.TransactionEntry.COLUMN_BUS_LINE + " TEXT," +
                        DBContract.TransactionEntry.COLUMN_CAR_NO + " TEXT," +
                        DBContract.TransactionEntry.COLUMN_TIME + " TEXT," +
                        DBContract.TransactionEntry.COLUMN_IS_UPLOADED + " INTEGER)"

        private val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + DBContract.TransactionEntry.TABLE_NAME
    }
}