package com.app.nikhil.youtuberenderingapp.Rest;

public interface ResponseCallback<T> {

    void success(T t);

    void  failure(T t);


}
