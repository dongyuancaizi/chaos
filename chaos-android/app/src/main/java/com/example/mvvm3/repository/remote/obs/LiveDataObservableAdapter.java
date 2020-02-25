package com.example.mvvm3.repository.remote.obs;


import androidx.lifecycle.LiveData;

import com.example.mvvm3.model.Lcee;

import io.reactivex.Observable;

/**
 * Created by 86839 on 2017/10/21.
 */

public class LiveDataObservableAdapter {
    public static <T> LiveData<T> fromObservable(final Observable<T> observable) {
        return new ObservableLiveData<>(observable);
    }

    public static <T> LiveData<Lcee<T>> fromObservableLcee(final Observable<T> observable) {
        return new ObservableLceeLiveData<>(observable);
    }

}
