package com.example.mvvm3.repository.local;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.mvvm3.repository.local.dao.UserDao;
import com.example.mvvm3.model.User;

/**
 * @author Jian.cui
 * @date 2019/11/4 19:21
 */


@Database(entities = {User.class}, version = 1, exportSchema = false)
public abstract class DB extends RoomDatabase {
    public abstract UserDao getUserDao();

}
