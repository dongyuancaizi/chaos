package com.example.mvvm3.repository.local;
/**
 * @author Jian.cui
 * @date 2019/11/4 19:26
 */

import android.os.AsyncTask;

import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;

import com.example.mvvm3.repository.local.service.UserService;
import com.example.mvvm3.repository.local.service.impl.UserServiceImpl;
import com.example.mvvm3.model.Lcee;
import com.example.mvvm3.model.User;
import com.example.mvvm3.repository.UserDataSource;

public class LocalUserDataSource implements UserDataSource {
    private static final LocalUserDataSource instance = new LocalUserDataSource();
    public LocalUserDataSource() {
    }
    public static LocalUserDataSource getInstance() {
        return instance;
    }


    private UserService userService = UserServiceImpl.getInstance();

    @Override
    public LiveData<Lcee<User>> queryUserLceeByUsername(String username) {
        final MediatorLiveData<Lcee<User>> data = new MediatorLiveData<>();
        data.setValue(Lcee.<User>loading());

        data.addSource(userService.queryByUsernameSync(username), new Observer<User>() {
            @Override
            public void onChanged(@Nullable User user) {
                if (null == user) {
                    data.setValue(Lcee.<User>empty());
                } else {
                    data.setValue(Lcee.content(user));
                }
            }
        });
        return data;
    }

    public LiveData<Long> addUserSync(User user) {
        return userService.add(user);
    }
    public void addUser(final User user) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                userService.add(user);
                return null;
            }
        }.execute();
    }

    @Override
    public void queryUserByUsername(final String username, final Result<User> result) {
        new AsyncTask<Void, Void, Object>() {

            @Override
            protected Object doInBackground(Void... voids) {
                try {
                    User user = userService.queryByUsername(username);
                    return user;
                } catch (Exception e) {
                    e.printStackTrace();
                    return e;
                }
            }

            @Override
            protected void onPostExecute(Object obj) {
                if (obj instanceof User)
                    result.onSuccess((User) obj);
                else if (obj instanceof Exception)
                    result.onFailed((Throwable) obj);
                else
                    result.onFailed(null);
            }
        }.execute();

    }

}