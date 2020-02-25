package com.example.mvvm3.repository.remote;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.mvvm3.model.Lcee;
import com.example.mvvm3.repository.remote.api.UserApi;
import com.example.mvvm3.model.User;
import com.example.mvvm3.repository.UserDataSource;
import com.example.mvvm3.repository.local.LocalUserDataSource;
import com.example.mvvm3.repository.remote.obs.LiveDataObservableAdapter;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author Jian.cui
 * @date 2019/11/4 19:26
 */


public class RemoteUserDataSource implements UserDataSource {
    private static final RemoteUserDataSource instance = new RemoteUserDataSource();

    public RemoteUserDataSource() {
    }

    public static RemoteUserDataSource getInstance() {
        return instance;
    }

    private UserApi userApi = RetrofitFactory.getInstance().create(UserApi.class);

    @Override
    public LiveData<Lcee<User>> queryUserLceeByUsername(String username) {
        return LiveDataObservableAdapter.fromObservableLcee(
                userApi.queryUserByUsername(username)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
        );
    }

    @Override
    public void queryUserByUsername(String username, final Result<User> result) {
//        userApi.queryUserByUsername(username)
//                .enqueue(new Callback<User>() {
//                    @Override
//                    public void onResponse(Call<User> call, Response<User> response) {
//                        User user = response.body();
//                        result.onSuccess(user);
//                        // update cache
//                        LocalUserDataSource.getInstance().addUser(user);
//                    }
//
//                    @Override
//                    public void onFailure(Call<User> call, Throwable t) {
//                        t.printStackTrace();
//                        result.onFailed(t);
//                    }
//                });
    }
}