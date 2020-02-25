package com.example.mvvm3.repository.local.service.impl;
/**
 * @author Jian.cui
 * @date 2019/11/4 19:22
 */

import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.mvvm3.repository.local.DBHelper;
import com.example.mvvm3.repository.local.dao.UserDao;
import com.example.mvvm3.repository.local.service.UserService;
import com.example.mvvm3.model.User;

public class UserServiceImpl implements UserService {
    private static final UserServiceImpl instance = new UserServiceImpl();

    public UserServiceImpl() {
    }

    public static UserServiceImpl getInstance() {
        return instance;
    }


    private UserDao userDao = DBHelper.getInstance().getDb().getUserDao();

    @Override
    public LiveData<Long> add(final User user) {
        // transfer long to LiveData<Long>
        final MutableLiveData<Long> data = new MutableLiveData<>();
        new AsyncTask<Void, Void, Long>() {
            @Override
            protected Long doInBackground(Void... voids) {
                return userDao.add(user);
            }

            @Override
            protected void onPostExecute(Long rowId) {
                data.setValue(rowId);
            }
        }.execute();
        return data;
    }

    @Override
    public LiveData<User> queryByUsernameSync(String username) {
        return userDao.queryByUsernameSync(username);
    }

    @Override
    public User queryByUsername(String username) {
        return userDao.queryByUsername(username);
    }

}