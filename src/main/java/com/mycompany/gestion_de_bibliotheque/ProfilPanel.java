package com.mycompany.gestion_de_bibliotheque;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ProfilPanel extends JPanel {
    private JTextField nameField;
    private JPasswordField passwordField;
    private JTextField emailField;
    private JButton saveButton;
    private Personne connectedUser;

    public ProfilPanel(Personne user) {
        this.connectedUser = user;

        setLayout(new BorderLayout());

        // Panneau d'informations du profil utilisateur
        JPanel profileInfoPanel = new JPanel(new GridLayout(4, 2));
        JLabel nameLabel = new JLabel("Nom:");
        nameField = new JTextField(connectedUser.getNom(), 20);
        JLabel passwordLabel = new JLabel("Mot de passe:");
        passwordField = new JPasswordField(connectedUser.getPassword(), 20);
        JLabel emailLabel = new JLabel("Email:");
        emailField = new JTextField(connectedUser.getEmail(), 20);
        saveButton = new JButton("Enregistrer");

        profileInfoPanel.add(nameLabel);
        profileInfoPanel.add(nameField);
        profileInfoPanel.add(passwordLabel);
        profileInfoPanel.add(passwordField);
        profileInfoPanel.add(emailLabel);
        profileInfoPanel.add(emailField);
        profileInfoPanel.add(new JLabel()); // Espacement
        profileInfoPanel.add(saveButton);

        add(profileInfoPanel, BorderLayout.NORTH);

        // Ajouter un écouteur d'événements pour le bouton Enregistrer
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Enregistrer les modifications du profil
                String newName = nameField.getText();
                String newPassword = new String(passwordField.getPassword());
                String newEmail = emailField.getText();
                boolean userModified = modifierUser(connectedUser.getId(), newName, newPassword, newEmail);

                if (userModified) {
                    JOptionPane.showMessageDialog(ProfilPanel.this, "Profil enregistré avec succès!");
                } else {
                    JOptionPane.showMessageDialog(ProfilPanel.this, "Erreur lors de l'enregistrement du profil!", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    // Méthode pour modifier les informations de l'utilisateur
    private boolean modifierUser(int id, String nom, String password, String email) {
        // Implémentez votre méthode pour mettre à jour les informations de l'utilisateur dans la base de données
        // Ici, nous supposons que vous utilisez une classe de connexion à la base de données nommée Jdbc
        try (Connection conn = Jdbc.getConnection()) {
            String query = "UPDATE personne SET nom=?, password=?, email=? WHERE personne_id=?";
            try (PreparedStatement statement = conn.prepareStatement(query)) {
                statement.setString(1, nom);
                statement.setString(2, password);
                statement.setString(3, email);
                statement.setInt(4, id);
                int rowsUpdated = statement.executeUpdate();
                return rowsUpdated > 0;
            }
        } catch (SQLException ex) {
            System.out.println("Erreur lors de la mise à jour de l'utilisateur : " + ex.getMessage());
            return false;
        }
    }
}
