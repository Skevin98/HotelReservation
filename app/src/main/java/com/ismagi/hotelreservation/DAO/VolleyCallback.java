package com.ismagi.hotelreservation.DAO;

public interface VolleyCallback<T> {
    void onSuccess(T result);
    void onError(String e);
}
