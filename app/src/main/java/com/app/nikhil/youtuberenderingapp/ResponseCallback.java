package com.app.nikhil.youtuberenderingapp;

public interface ResponseCallback<T> {

    void success(T t);

    void  failure(T t);


}
