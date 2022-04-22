package com.ismagi.hotelreservation.DAO;

import com.ismagi.hotelreservation.Models.Chambre;

import java.util.List;

interface IChambreDao extends IDao<Chambre> {

    List<Chambre> GetChambreByCategorie(String IdCategorie);
}
