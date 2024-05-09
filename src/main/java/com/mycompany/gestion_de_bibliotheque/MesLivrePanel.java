/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestion_de_bibliotheque;

/**
 *
 * @author asma
 */


import com.mycompany.gestion_de_bibliotheque.Session;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MesLivrePanel extends JPanel {
    
      private JButton retournButton;
    private JTable livresTable;
    private DefaultTableModel tableModel;
    private Bibliotheque bibliotheque;
    private Personne connectedUser; 
    public MesLivrePanel(Bibliotheque bibliotheque) {
    
        this.bibliotheque = bibliotheque;
        this.connectedUser = Session.getInstance().getConnectedUser(); // Récupération de l'utilisateur connecté depuis Session

        setLayout(new BorderLayout());

        // Retrieve available books from the library
         int id_personne = connectedUser.getId(); 


          
        java.util.List<Livre> livres = getLivresEmpruntesParUtilisateur(id_personne);

        // Create the table with book data
        String[] columnNames = {"Id" ,"Titre", "Auteur"};
        Object[][] rowData = new Object[livres.size()][3];
        for (int i = 0; i < livres.size(); i++) {
            Livre livre = livres.get(i);
             rowData[i][0] = livre.getId();
            rowData[i][1] = livre.getTitre();
            rowData[i][2] = livre.getAuteur();
            
        }
        tableModel = new DefaultTableModel(rowData, columnNames);
        livresTable = new JTable(tableModel);

        JScrollPane scrollPane = new JScrollPane(livresTable);
        add(scrollPane, BorderLayout.CENTER);

        // Borrow button
        retournButton = new JButton("Returner");
        retournButton.addActionListener(e -> {
            // Retrieve selected books
          // Obtenez l'id_personne de l'utilisateur connecté

            int[] selectedRows = livresTable.getSelectedRows();
            for (int row : selectedRows) {
                
                if (connectedUser != null) {
                Livre livre = livres.get(row);
                // Example action: borrow the book via the library
// Example User object
                 int id_livre = livre.getId(); // Supposons que votre Livre a une méthode getId() pour obtenir l'ID
                  // Supposons que votre Livre a une méthode getId() pour obtenir l'ID
                  
                 boolean disponibiliteModifiee = modifierDisponibiliteLivre(id_livre, true); // Modifie la disponibilité du livre
                 boolean dateModifiee = modifierDateRetour(id_livre); 
                  System.out.println(id_livre +"    "+disponibiliteModifiee+"  "+dateModifiee);

                tableModel.removeRow(row); // Supprimer la ligne du livre emprunté de l'interface
                JOptionPane.showMessageDialog(null, "Livre retourner avec succès !");
            } else {
                JOptionPane.showMessageDialog(null, "Erreur lors de le retour du livre.");
            }
       
    }
              
            
            // Example: display borrow and return dates
        });

        add(retournButton, BorderLayout.SOUTH);
    }

    public List<Livre> getLivresEmpruntesParUtilisateur(int idUtilisateur) {
        List<Livre> livresEmpruntes = new ArrayList<>();

        try (Connection conn = Jdbc.getConnection()) {
            String query = "SELECT livre.livre_id, livre.titre, livre.auteur " +
                           "FROM emprunt " +
                           "INNER JOIN livre ON emprunt.livre_id = livre.livre_id " +
                           "WHERE emprunt.personne_id = ? and emprunt.date_retour is null";
            try (PreparedStatement statement = conn.prepareStatement(query)) {
                statement.setInt(1, idUtilisateur);
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        int idLivre = resultSet.getInt("livre_id");
                        String titre = resultSet.getString("titre");
                        String auteur = resultSet.getString("auteur");
                        Livre livre = new Livre(idLivre, titre, auteur);
                        livresEmpruntes.add(livre);
                    }
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error fetching borrowed books: " + ex.getMessage());
        }

        return livresEmpruntes;
    }
    
    private boolean modifierDisponibiliteLivre(int id_livre, boolean disponibilite) {
    try (Connection conn = Jdbc.getConnection()) {
        String query = "UPDATE livre SET disponible = ? WHERE livre_id = ?";
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setBoolean(1, disponibilite);
            statement.setInt(2,id_livre);
            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated > 0;
        }
    } catch (SQLException ex) {
        System.out.println("Error updating book availability: " + ex.getMessage());
        return false;
    }
}
    
    private boolean modifierDateRetour(int id_livre ) {
    try (Connection conn = Jdbc.getConnection()) {
       
    
        String query = "UPDATE emprunt SET date_retour = NOW() WHERE livre_id = ?";
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setInt(1,id_livre);
            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated > 0;
        }
    } catch (SQLException ex) {
        System.out.println("Error updating date retour: " + ex.getMessage());
        return false;
    }
} 
    
    
}
