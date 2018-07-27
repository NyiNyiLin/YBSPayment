package com.nyi.ybspayment.db;

/**
 * Created by IN-3442 on 27-Jul-18.
 */

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.nyi.ybspayment.YbsPayment;

@Database(entities = {Transaction.class}, version = 1, exportSchema = false)
public abstract class AppDatabaseJava extends RoomDatabase{
    public abstract TransactionDao TransactionDao();

    private static AppDatabaseJava instance;

    public static AppDatabaseJava getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context,
                    AppDatabaseJava.class, "ybspayment.db")
                            .build();
        }
        return instance;
    }

}
