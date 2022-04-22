package com.ismagi.hotelreservation.Models;

import java.util.Date;
import java.util.UUID;

public class Reservation {

    public Reservation() {
        this.idReservation = UUID.randomUUID().toString();
    }


    public String getIdReservation() {
        return idReservation;
    }

    public void setIdReservation(String idReservation) {
        this.idReservation = idReservation;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public Chambre[] getChambres() {
        return chambres;
    }

    public void setChambres(Chambre[] chambres) {
        this.chambres = chambres;
    }

    public Date getDateReservation() {
        return dateReservation;
    }

    public void setDateReservation(Date dateReservation) {
        this.dateReservation = dateReservation;
    }

    public Date getDateEntree() {
        return dateEntree;
    }

    public void setDateEntree(Date dateEntree) {
        this.dateEntree = dateEntree;
    }

    public Date getDateSortie() {
        return dateSortie;
    }

    public void setDateSortie(Date dateSortie) {
        this.dateSortie = dateSortie;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public int getNbEnfant() {
        return NbEnfant;
    }

    public void setNbEnfant(int nbEnfant) {
        NbEnfant = nbEnfant;
    }

    public int getNbAdult() {
        return NbAdult;
    }

    public void setNbAdult(int nbAdult) {
        NbAdult = nbAdult;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    String idReservation;
    String idUser;
    Chambre[] chambres = null;
    Date dateReservation;
    Date dateEntree;
    Date dateSortie;
    boolean isActive;
    int NbEnfant;
    int NbAdult;
    double montant;
}
