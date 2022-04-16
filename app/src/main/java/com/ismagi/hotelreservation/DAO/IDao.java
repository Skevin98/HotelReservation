package com.ismagi.hotelreservation.DAO;

import java.util.List;

interface IDao<T> {

    void Add(T obj);
    T GetById(String id);
    void Update(T obj);
    void Delete(T obj);
    List<T> GetAll();

}
