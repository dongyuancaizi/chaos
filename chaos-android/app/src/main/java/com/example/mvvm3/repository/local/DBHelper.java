package com.example.mvvm3.repository.local;

import android.content.Context;

import androidx.room.Room;

/**
 * @author Jian.cui
 * @date 2019/11/4 19:21
 */


public class DBHelper {
    private static final DBHelper instance = new DBHelper();
    private static final String DATABASE_NAME = "c_cache";

    private DBHelper() {

    }

    public static DBHelper getInstance() {
        return instance;
    }

    private DB db;

    public void init(Context context) {
        db = Room.databaseBuilder(context.getApplicationContext(), DB.class, DATABASE_NAME).build();
    }

    public DB getDb() {
        return db;
    }
}