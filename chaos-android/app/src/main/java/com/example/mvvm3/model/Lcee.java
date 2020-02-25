package com.example.mvvm3.model;

import com.example.mvvm3.utils.Status;

/**
 * @author Jian.cui
 * @date 2019/11/4 19:52
 */

public class Lcee<T> {
    public final Status status;
    public final T data;
    public final Throwable error;

    public Lcee(Status status, T data, Throwable error) {
        this.status = status;
        this.data = data;
        this.error = error;
    }
    public static <T> Lcee<T> content(T data) {
        return new Lcee<>(Status.Content, data, null);
    }

    public static <T> Lcee<T> error(T data, Throwable error) {
        return new Lcee<>(Status.Error, data, error);
    }
    public static <T> Lcee<T> error(Throwable error) {
        return error(null, error);
    }

    public static <T> Lcee<T> empty(T data) {
        return new Lcee<>(Status.Empty, data, null);
    }
    public static <T> Lcee<T> empty() {
        return empty(null);
    }

    public static <T> Lcee<T> loading(T data) {
        return new Lcee<>(Status.Loading, data, null);
    }
    public static <T> Lcee<T> loading() {
        return loading(null);
    }
}