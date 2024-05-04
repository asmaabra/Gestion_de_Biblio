package com.mycompany.gestion_de_bibliotheque;

public class Personne {
    private String nom;
    private String password;
    private String role;

    public Personne(String nom, String role, String password) {
        this.nom = nom;
        this.role = role;
        this.password=password;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setRole(String role) {
        this.role = role;
    }

    // Getters et Setters pour les attributs nom et role

    public String getNom() {
        return nom;
    }

    public String getRole() {
        return role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
