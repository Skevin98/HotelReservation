package com.ismagi.hotelreservation.DAO;

import com.ismagi.hotelreservation.models.Chambre;

import java.util.List;

public interface IChambreDao extends IDao<Chambre> {

    List<Chambre> GetChambreByCategorie(String IdCategorie, VolleyCallback callback);
}
