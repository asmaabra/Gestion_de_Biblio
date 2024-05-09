package com.mycompany.gestion_de_bibliotheque;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class UtilisateurGI extends JFrame {
    private final String username; // Variable pour stocker le nom d'utilisateur

    // Composants de l'interface utilisateur
    private final JPanel sidebarPanel;
    private final JPanel mainPanel;
    private final JButton libraryButton;
    private final JButton myBooksButton;
    private final JButton profileButton;
    private final JButton logoutButton;

    public UtilisateurGI(String username) {
        this.username = username;

        setTitle("Tableau de bord utilisateur");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        // Création des boutons de la barre latérale
        libraryButton = new JButton("Bibliothèque");
        myBooksButton = new JButton("Mes Livres");
        profileButton = new JButton("Mon Profil");
        logoutButton = new JButton("Déconnecter");

        // Création des panneaux
        sidebarPanel = createSidebarPanel();
        mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.WHITE);
        JLabel welcomeLabel = new JLabel("Bienvenue, " + username + " !");
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        mainPanel.add(welcomeLabel, BorderLayout.CENTER);

        // Ajout des écouteurs d'événements pour les boutons de la barre latérale
        libraryButton.addActionListener(e -> displayLibrary());
        myBooksButton.addActionListener(e -> displayMyBooks());
        profileButton.addActionListener(e -> displayProfile());
        logoutButton.addActionListener(e -> logout());

        // Ajout des composants à la fenêtre principale
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(sidebarPanel, BorderLayout.WEST);
        getContentPane().add(mainPanel, BorderLayout.CENTER);
    }

    // Création du panneau de la barre latérale
    private JPanel createSidebarPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(Color.LIGHT_GRAY);
        panel.setLayout(new GridLayout(4, 1));

        // Définition de la taille des boutons
        Dimension buttonSize = new Dimension(150, 30);
        libraryButton.setPreferredSize(buttonSize);
        myBooksButton.setPreferredSize(buttonSize);
        profileButton.setPreferredSize(buttonSize);
        logoutButton.setPreferredSize(buttonSize);

        // Ajout des boutons à la barre latérale
        panel.add(libraryButton);
        panel.add(myBooksButton);
        panel.add(profileButton);
        panel.add(logoutButton);

        return panel;
    }

    // Méthode pour afficher la bibliothèque
    private void displayLibrary() {
        try (Connection connection = Jdbc.getConnection()) {
            LivreDAO livreDAO = new LivreDAO(connection);
            List<Livre> livres = livreDAO.getAllLivres();
            Bibliotheque bibliotheque = new Bibliotheque();
            for (Livre livre : livres) {
                bibliotheque.ajouterLivre(livre);
            }
            BiblioPanel biblioPanel = new BiblioPanel(bibliotheque);
            updateMainPanel(biblioPanel);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erreur lors du chargement de la bibliothèque: " + ex.getMessage());
        }
    }

    // Méthode pour afficher les livres de l'utilisateur
    private void displayMyBooks() {
        try (Connection connection = Jdbc.getConnection()) {
            LivreDAO livreDAO = new LivreDAO(connection);
            List<Livre> livres = livreDAO.getAllLivres(); // Change to fetch user's books
            Bibliotheque bibliotheque = new Bibliotheque();
            for (Livre livre : livres) {
                bibliotheque.ajouterLivre(livre);
            }
            MesLivrePanel livrePanel = new MesLivrePanel(bibliotheque);
            updateMainPanel(livrePanel);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erreur lors du chargement de vos livres: " + ex.getMessage());
        }
    }

    // Méthode pour afficher le profil de l'utilisateur
    private void displayProfile() {
        mainPanel.removeAll();
        ProfilPanel profilPanel = new ProfilPanel(Jdbc.getUserByUsername(username));
        mainPanel.add(profilPanel, BorderLayout.NORTH);
        mainPanel.revalidate();
        mainPanel.repaint();
    }

    // Méthode pour mettre à jour le panneau principal
    private void updateMainPanel(JPanel newPanel) {
        mainPanel.removeAll();
        mainPanel.add(newPanel, BorderLayout.CENTER);
        mainPanel.revalidate();
        mainPanel.repaint();
    }

    // Méthode pour la déconnexion de l'utilisateur
    private void logout() {
        JOptionPane.showMessageDialog(null, "Vous êtes déconnecté !");
        dispose(); // Ferme la fenêtre de l'application
    }
}
