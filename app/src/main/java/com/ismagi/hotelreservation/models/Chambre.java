package com.ismagi.hotelreservation.models;

public class Chambre {

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public int getNumero() {
        return Numero;
    }

    public void setNumero(int numero) {
        Numero = numero;
    }

    public int getNumEtage() {
        return NumEtage;
    }

    public void setNumEtage(int numEtage) {
        NumEtage = numEtage;
    }

    public int getNbLits() {
        return NbLits;
    }

    public void setNbLits(int nbLits) {
        NbLits = nbLits;
    }

    public com.ismagi.hotelreservation.models.Categorie getCategorie() {
        return Categorie;
    }

    public void setCategorie(com.ismagi.hotelreservation.models.Categorie categorie) {
        Categorie = categorie;
    }

    public boolean isAvailable() {
        return IsAvailable;
    }

    public void setAvailable(boolean available) {
        IsAvailable = available;
    }

    public boolean isHasBalcon() {
        return HasBalcon;
    }

    public void setHasBalcon(boolean hasBalcon) {
        HasBalcon = hasBalcon;
    }

    public boolean isHasVue_sur_mer() {
        return HasVue_sur_mer;
    }

    public void setHasVue_sur_mer(boolean hasVue_sur_mer) {
        HasVue_sur_mer = hasVue_sur_mer;
    }

    public boolean isHasSalle_sejour() {
        return HasSalle_sejour;
    }

    public void setHasSalle_sejour(boolean hasSalle_sejour) {
        HasSalle_sejour = hasSalle_sejour;
    }

    public Boolean getHasCuisine() {
        return HasCuisine;
    }

    public void setHasCuisine(Boolean hasCuisine) {
        HasCuisine = hasCuisine;
    }

    String Id;
    int Numero;
    int NumEtage;
    int NbLits;
    Categorie Categorie;
    boolean IsAvailable;
    boolean HasBalcon;
    boolean HasVue_sur_mer;
    boolean HasSalle_sejour;
    Boolean HasCuisine;

}
