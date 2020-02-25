package com.example.mvvm3.repository;

import androidx.lifecycle.LiveData;

import com.example.mvvm3.model.Lcee;
import com.example.mvvm3.model.User;

/**
 * @author Jian.cui
 * @date 2019/11/4 19:25
 */


public interface UserDataSource {
    LiveData<Lcee<User>> queryUserLceeByUsername(String username);
    interface Result<T> {
        void onSuccess(T data);
        void onFailed(Throwable throwable);
    }

    void queryUserByUsername(String username, Result<User> result);
}