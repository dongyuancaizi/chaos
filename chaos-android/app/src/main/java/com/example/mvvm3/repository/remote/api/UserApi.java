package com.example.mvvm3.repository.remote.api;
/**
 * @author Jian.cui
 * @date 2019/11/4 17:41
 */

import com.example.mvvm3.model.User;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface UserApi {
    @GET("/users/{username}")
    Observable<User> queryUserByUsername(@Path("username") String username);
}
