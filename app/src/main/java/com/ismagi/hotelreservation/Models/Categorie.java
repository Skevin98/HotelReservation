package com.ismagi.hotelreservation.Models;

public class Categorie {

    public String getIdCategorie() {
        return IdCategorie;
    }

    public void setIdCategorie(String idCategorie) {
        IdCategorie = idCategorie;
    }

    public String getLibelle() {
        return Libelle;
    }

    public void setLibelle(String libelle) {
        Libelle = libelle;
    }

    public double getTarif() {
        return Tarif;
    }

    public void setTarif(double tarif) {
        Tarif = tarif;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    protected String IdCategorie;
    protected String Libelle;
    protected double Tarif;
    protected String Description;
}
