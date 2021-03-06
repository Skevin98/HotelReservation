package com.ismagi.hotelreservation.DAO;

import com.ismagi.hotelreservation.models.Reservation;

public interface IReservationDAO extends IDao<Reservation> {

    void GetReservationByUser(String idUser, VolleyCallback callback);
}
