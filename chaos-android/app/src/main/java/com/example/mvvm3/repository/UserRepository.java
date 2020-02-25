package com.example.mvvm3.repository;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.mvvm3.repository.local.LocalUserDataSource;
import com.example.mvvm3.repository.remote.RemoteUserDataSource;
import com.example.mvvm3.utils.NetworkUtils;
import com.example.mvvm3.model.Lcee;
import com.example.mvvm3.repository.remote.api.UserApi;
import com.example.mvvm3.model.User;
import com.example.mvvm3.repository.remote.RetrofitFactory;

/**
 * @author Jian.cui
 * @date 2019/11/4 17:50
 */


public class UserRepository {
    private Context context;
    private UserDataSource remoteUserDataSource = RemoteUserDataSource.getInstance();
    private UserDataSource localUserDataSource = LocalUserDataSource.getInstance();

    private static final UserRepository instance = new UserRepository();

    public UserRepository() {
    }

    public static UserRepository getInstance() {
        return instance;
    }

    private UserApi userApi = RetrofitFactory.getInstance().create(UserApi.class);

    public void init(Context context) {
        this.context = context.getApplicationContext();
    }

    public LiveData<Lcee<User>> getUser(String username) {
        if (NetworkUtils.isConnected(context)) {
            return getUserFromRemote(username);
        } else {
            return getUserFromLocal(username);
        }
    }

    private LiveData<Lcee<User>> getUserFromRemote(String username) {
        return getUserFromDataSource(remoteUserDataSource, username);
    }

    private LiveData<Lcee<User>> getUserFromLocal(String username) {
        return getUserFromDataSource(localUserDataSource, username);
    }

    private LiveData<Lcee<User>> getUserFromDataSource(UserDataSource dataSource, String username) {
        final MutableLiveData<Lcee<User>> data = new MutableLiveData<>();
        data.setValue(Lcee.<User>loading());
        dataSource.queryUserByUsername(username, new UserDataSource.Result<User>() {
            @Override
            public void onSuccess(User user) {
                if (null == data)
                    data.setValue(Lcee.<User>empty());
                else
                    data.setValue(Lcee.content(user));
            }

            @Override
            public void onFailed(Throwable throwable) {
                data.setValue(Lcee.<User>error(throwable));
            }
        });
        return data;
    }
}