/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestion_de_bibliotheque;

/**
 *
 * @author asma
 */
public class Livre {
    private String id_livre;
    private String titre;
    private String auteur;
    private boolean disponibilite; // true si le livre est disponible, false sinon

    public Livre(String titre, String auteur, boolean disponibilite) {
        this.titre = titre;
        this.auteur = auteur;
        this.disponibilite = disponibilite;
    }

    public String getTitre() {
        return titre;
    }

    public String getAuteur() {
        return auteur;
    }

    public boolean isDisponible() {
        return disponibilite;
    }

    public void setDisponible(boolean disponibilite) {
        this.disponibilite = disponibilite;
    }
}

