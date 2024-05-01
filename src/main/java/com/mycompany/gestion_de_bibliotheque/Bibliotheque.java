/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestion_de_bibliotheque;

/**
 *
 * @author asma
 */

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Bibliotheque {
    private List<Livre> livres;
    private List<Emprunt> emprunts;

    public Bibliotheque() {
        livres = new ArrayList<>();
        // Initialiser les livres...
        livres.add(new Livre("Titre 1", "Auteur 1", true));
    livres.add(new Livre("Titre 2", "Auteur 2", false));
    livres.add(new Livre("Titre 3", "Auteur 3", true));
        emprunts = new ArrayList<>();
    }

   
       

public List<Livre> getLivresDisponibles() {
    List<Livre> livresDisponibles = new ArrayList<>();
    for (Livre livre : livres) {
        if (livre.isDisponible()) {
            livresDisponibles.add(livre);
        }
    }
    return livresDisponibles;
} 
    
    public void emprunterLivre(Livre livre, Date dateEmprunt, Date dateRetour) {
        livre.setDisponible(false);
        emprunts.add(new Emprunt(livre, dateEmprunt, dateRetour));
    }

    public void retournerLivre(Emprunt emprunt) {
        emprunts.remove(emprunt);
        emprunt.getLivre().setDisponible(true);
    }
}


