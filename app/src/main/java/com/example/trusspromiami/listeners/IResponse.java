package com.example.trusspromiami.listeners;

public interface IResponse<T, E> {
    void onSuccess(T result);

    void onFailure(E error);
}
