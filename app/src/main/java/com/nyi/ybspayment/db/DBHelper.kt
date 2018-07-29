package com.nyi.ybspayment.db

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteConstraintException
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import com.nyi.ybspayment.db.model.TransactionModel
import com.nyi.ybspayment.db.model.UserModel

/**
 * Created by IN-3442 on 27-Jul-18.
 */
class DBHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(SQL_CREATE_TRANSACTION_TABLE_ENTRIES)
        db.execSQL(SQL_CREATE_USER_TABLE_ENTRIES)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_TRANSACTION_TABLE_ENTRIES)
        db.execSQL(SQL_DELETE_USER_TABLE_ENTRIES)
        onCreate(db)
    }

    override fun onDowngrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        onUpgrade(db, oldVersion, newVersion)
    }

    @Throws(SQLiteConstraintException::class)
    fun insertTransaction(transaction: TransactionModel): Boolean {
        // Gets the data repository in write mode
        val db = writableDatabase

        val values = TransactionModel.modelToContentValue(transaction)

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

    fun readTransaction(transactionID: Int): ArrayList<TransactionModel> {
        val transactionList = ArrayList<TransactionModel>()
        val db = writableDatabase
        var cursor: Cursor? = null
        try {
            cursor = db.rawQuery("select * from " + DBContract.TransactionEntry.TABLE_NAME + " WHERE " + DBContract.TransactionEntry.COLUMN_TRANSACTION_ID + "='" + transactionID + "'", null)
        } catch (e: SQLiteException) {
            // if table not yet present, create it
            db.execSQL(SQL_CREATE_TRANSACTION_TABLE_ENTRIES)
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
            db.execSQL(SQL_CREATE_TRANSACTION_TABLE_ENTRIES)
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

    fun readAllUser() : UserModel{
        var db = writableDatabase
        var user : UserModel = UserModel()

        var cursor: Cursor? = null
        try {
            cursor = db.rawQuery("select * from " + DBContract.UserEntry.TABLE_NAME, null)
        } catch (e: SQLiteException) {
            db.execSQL(SQL_CREATE_USER_TABLE_ENTRIES)
            return UserModel()
        }

        if (cursor!!.moveToFirst()) {
            while (cursor.isAfterLast == false) {
                user = UserModel.cursorToModel(cursor)
                cursor.moveToNext()
            }
        }
        return user
    }

    @Throws(SQLiteConstraintException::class)
    fun insertUser(user: UserModel): Boolean {
        // Gets the data repository in write mode
        val db = writableDatabase

        val values = UserModel.modelToContentValue(user)

        // Insert the new row, returning the primary key value of the new row
        val newRowId = db.insert(DBContract.UserEntry.TABLE_NAME, null, values)

        return true
    }

    @Throws(SQLiteConstraintException::class)
    fun updateAvailAmount(userID : String, availAmount : Int){
        var db = writableDatabase

        val cv : ContentValues = ContentValues()
        cv.put(DBContract.UserEntry.COLUMN_AVAIL_AMOUNT, availAmount)

        db.update(DBContract.UserEntry.TABLE_NAME, cv, DBContract.UserEntry.COLUMN_USER_ID + " = ?", arrayOf(userID))
    }

    companion object {
        // If you change the database schema, you must increment the database version.
        val DATABASE_VERSION = 1
        val DATABASE_NAME = "ybspayment.db"

        private val SQL_CREATE_TRANSACTION_TABLE_ENTRIES =
                "CREATE TABLE " + DBContract.TransactionEntry.TABLE_NAME + " (" +
                        DBContract.TransactionEntry.COLUMN_TRANSACTION_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                        DBContract.TransactionEntry.COLUMN_PHONE_No + " TEXT," +
                        DBContract.TransactionEntry.COLUMN_BUS_LINE + " TEXT," +
                        DBContract.TransactionEntry.COLUMN_CAR_NO + " TEXT," +
                        DBContract.TransactionEntry.COLUMN_TIME + " TEXT," +
                        DBContract.TransactionEntry.COLUMN_IS_UPLOADED + " INTEGER)"

        private val SQL_DELETE_TRANSACTION_TABLE_ENTRIES = "DROP TABLE IF EXISTS " + DBContract.TransactionEntry.TABLE_NAME

        private val SQL_CREATE_USER_TABLE_ENTRIES =
                "CREATE TABLE " + DBContract.UserEntry.TABLE_NAME + " (" +
                        DBContract.UserEntry.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                        DBContract.UserEntry.COLUMN_USER_ID + " TEXT," +
                        DBContract.UserEntry.COLUMN_PHONE_NO + " TEXT," +
                        DBContract.UserEntry.COLUMN_AVAIL_AMOUNT + " INTEGER)"

        private val SQL_DELETE_USER_TABLE_ENTRIES = "DROP TABLE IF EXISTS " + DBContract.TransactionEntry.TABLE_NAME

    }
}