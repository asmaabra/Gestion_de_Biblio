/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestion_de_bibliotheque;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UtilisateurGI extends JFrame {
    private JPanel sidebarPanel;
    private JButton libraryButton, myBooksButton, profileButton, logoutButton;
    private JPanel mainPanel;

    public UtilisateurGI() {
        setTitle("Tableau de bord utilisateur");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        // Sidebar
        sidebarPanel = new JPanel();
        sidebarPanel.setBackground(Color.LIGHT_GRAY);
        sidebarPanel.setLayout(new GridLayout(4, 1));

        libraryButton = new JButton("Bibliothèque");
        myBooksButton = new JButton("Mes Livres");
        profileButton = new JButton("Mon Profil");
        logoutButton = new JButton("Déconnecter");

        // Définition de la taille des boutons
        Dimension buttonSize = new Dimension(150, 30);
        libraryButton.setPreferredSize(buttonSize);
        myBooksButton.setPreferredSize(buttonSize);
        profileButton.setPreferredSize(buttonSize);
        logoutButton.setPreferredSize(buttonSize);

        sidebarPanel.add(libraryButton);
        sidebarPanel.add(myBooksButton);
        sidebarPanel.add(profileButton);
        sidebarPanel.add(logoutButton);

        // Main Content
        mainPanel = new JPanel();
        mainPanel.setBackground(Color.WHITE);
        JLabel welcomeLabel = new JLabel("Bienvenue sur votre tableau de bord !");
        mainPanel.add(welcomeLabel);

        // Écouteurs de boutons pour Bibliothèque, Mes Livres, Mon Profil, Déconnecter
        libraryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
           
                Bibliotheque bibliotheque = new Bibliotheque(); // Création de la bibliothèque
                BiblioPanel biblioPanel = new BiblioPanel(bibliotheque); 
                mainPanel.removeAll();
                mainPanel.add(biblioPanel);
                mainPanel.revalidate();
                mainPanel.repaint();
            }
        });

        myBooksButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainPanel.removeAll();
                mainPanel.add(new MesLivrePanel());
                mainPanel.revalidate();
                mainPanel.repaint();
            }
        });

        profileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainPanel.removeAll();
                mainPanel.add(new ProfilPanel());
                mainPanel.revalidate();
                mainPanel.repaint();
            }
        });

        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Action à prendre lors de la déconnexion
                JOptionPane.showMessageDialog(null, "Vous êtes déconnecté !");
                // Pour cet exemple, nous fermons simplement la fenêtre
                dispose();
            }
        });

        // Add components to the main frame
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(sidebarPanel, BorderLayout.WEST);
        getContentPane().add(mainPanel, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            UtilisateurGI frame = new UtilisateurGI();
            frame.setVisible(true);
        });
    }
}
