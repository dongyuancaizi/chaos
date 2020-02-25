package com.example.mvvm3.repository.local.service;
/**
 * @author Jian.cui
 * @date 2019/11/4 19:22
 */

import androidx.lifecycle.LiveData;

import com.example.mvvm3.model.User;

public interface UserService {
    LiveData<Long> add(User user);

    LiveData<User> queryByUsernameSync(String username);

    User queryByUsername(String username);
}
