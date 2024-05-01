package com.mycompany.gestion_de_bibliotheque;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author asma
 */


import java.util.Date;

public class Emprunt {
    private String id_emp;
    private Livre livre;
    private Date dateEmprunt;
    private Date dateRetour;

    public Emprunt(Livre livre, Date dateEmprunt, Date dateRetour) {
        this.livre = livre;
        this.dateEmprunt = dateEmprunt;
        this.dateRetour = dateRetour;
    }

    public Livre getLivre() {
        return livre;
    }

    public Date getDateEmprunt() {
        return dateEmprunt;
    }

    public Date getDateRetour() {
        return dateRetour;
    }
}
