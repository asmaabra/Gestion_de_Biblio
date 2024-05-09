/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestion_de_bibliotheque;

/**
 *
 * @author asma
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



public class ProfilPanel extends JPanel {
    private JTextField nameField;
    private JPasswordField passwordField;
    private JTextField emailField;
    private JButton saveButton;
    private JButton logoutButton;
     private Personne connectedUser; 

    public ProfilPanel(String username) {
        this.connectedUser = Session.getInstance().getConnectedUser(); // Récupération de l'utilisateur connecté depuis Session

        setLayout(new BorderLayout());

        // User profile information panel
        JPanel profileInfoPanel = new JPanel(new GridLayout(4, 2));
        JLabel nameLabel = new JLabel("Name:");
        nameField = new JTextField(20);
        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField(20);
        JLabel emailLabel = new JLabel("Email:");
        emailField = new JTextField(20);
        saveButton = new JButton("Save");

        profileInfoPanel.add(nameLabel);
        profileInfoPanel.add(nameField);
        profileInfoPanel.add(passwordLabel);
        profileInfoPanel.add(passwordField);
        profileInfoPanel.add(emailLabel);
        profileInfoPanel.add(emailField);
        profileInfoPanel.add(new JLabel()); // Placeholder for spacing
        profileInfoPanel.add(saveButton);
        

        add(profileInfoPanel, BorderLayout.NORTH);
        // Add action listeners for buttons
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Save profile changes
               int id= connectedUser.getId();
                String newName = nameField.getText();
                String newPassword = new String(passwordField.getPassword());
                String newEmail = emailField.getText();
                boolean userModifiee = modifierUser(id,newName,newPassword,newEmail); // Modifie la disponibilité du livre
                System.out.println(id +"    "+userModifiee);


                // Update profile in the database or perform necessary actions
                JOptionPane.showMessageDialog(ProfilPanel.this, "Profile saved successfully!");
            }
        });

   
    }
     private boolean modifierUser(int id ,String nom,String password,String email) {
    try (Connection conn = Jdbc.getConnection()) {
       
    
        String query = "UPDATE personne SET nom=? , password=? , email=? WHERE personne_id = ?";
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setString(1,nom);
            statement.setString(2,password);
            statement.setString(3,email);
            statement.setInt(4,id);
            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated > 0;
        }
    } catch (SQLException ex) {
        System.out.println("Error updating users : " + ex.getMessage());
        return false;
    }
} 
    
        }
    

