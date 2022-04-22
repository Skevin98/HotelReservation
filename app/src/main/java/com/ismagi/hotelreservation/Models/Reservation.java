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

    public boolean isEtat() {
        return etat;
    }

    public void setEtat(boolean etat) {
        this.etat = etat;
    }

    public Personne[] getClients() {
        return clients;
    }

    public void setClients(Personne[] clients) {
        this.clients = clients;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    String idReservation;
    Chambre[] chambres = null;
    Date dateReservation;
    Date dateEntree;
    Date dateSortie;
    boolean etat;
    Personne[] clients = null;
    double montant;
}
