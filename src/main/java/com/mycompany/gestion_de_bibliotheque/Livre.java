package com.mycompany.gestion_de_bibliotheque;

public class Livre {
    private String idLivre; // Changed variable name to camelCase
    private String titre;
    private String auteur;
    private boolean disponibilite; // Changed variable name to camelCase

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

    public boolean isDisponible() { // Changed method name to follow convention
        return disponibilite;
    }

    public void setDisponible(boolean disponibilite) { // Changed method name to follow convention
        this.disponibilite = disponibilite;
    }
}
