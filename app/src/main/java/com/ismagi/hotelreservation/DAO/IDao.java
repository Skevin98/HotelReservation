package com.ismagi.hotelreservation.DAO;

import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import java.util.List;

public interface IDao<T> {

    void Add(T obj);
    void GetById(String id, VolleyCallback c);
    void Update(T obj, String id);
    void Delete(String id);
    void GetAll(VolleyCallback callback);

}
