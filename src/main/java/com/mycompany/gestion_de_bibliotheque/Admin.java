package com.mycompany.gestion_de_bibliotheque;

public class Admin extends Personne {
    public Admin(String nom, String password) {
        super(nom, "admin", password, null); // Assuming no email for admin
    }
}
