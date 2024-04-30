/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestion_de_bibliotheque;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

/**
 *
 * @author asma
 */
public class Signin extends JFrame {
     Color lightGreen = new Color(144, 238, 144);
    Color beige = new Color(245, 245, 220);
public  Signin(){
    
    setTitle("Sign up");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Charger l'image depuis le fichier
        ImageIcon backgroundImage = new ImageIcon("images/Accueil.jpeg");

        // Créer un JLabel avec l'image de fond
        JLabel backgroundLabel = new JLabel(backgroundImage);
        backgroundLabel.setBounds(0, 0, getWidth(), getHeight());

        // Créer un panneau pour organiser les composants
        JPanel contentPane = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        
        
        
        

        setContentPane(contentPane);
         JPanel contentPane1 = new JPanel();
         JLabel messageLabel = new JLabel("Entrer vos informations : ");
       messageLabel.setHorizontalAlignment(SwingConstants.CENTER); // Aligner le texte au centre
        messageLabel.setFont(new Font("Arial", Font.BOLD, 15)); // Définir la police et la taille du texte
         
        
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(8, 2));

        JLabel usernameLabel = new JLabel("Nom d'utilisateur:");
        JTextField usernameField = new JTextField(20);

        JLabel emailLabel = new JLabel("Adresse e-mail:");
        JTextField emailField = new JTextField(20);

        JLabel passwordLabel = new JLabel("Mot de passe:");
        JPasswordField passwordField = new JPasswordField(20);

        JLabel confirmPasswordLabel = new JLabel("Confirmer mot de passe:");
        JPasswordField confirmPasswordField = new JPasswordField(20);

        JButton signUpButton = new JButton("S'inscrire");
        signUpButton.addActionListener(e -> {
            // Logic to handle sign up button click
            String username = usernameField.getText();
            String email = emailField.getText();
            char[] password = passwordField.getPassword();
            char[] confirmPassword = confirmPasswordField.getPassword();

            // Perform validation and registration logic here
            // For demonstration, just printing the values
            System.out.println("Username: " + username);
            System.out.println("Email: " + email);
            System.out.println("Password: " + new String(password));
            System.out.println("Confirm Password: " + new String(confirmPassword));
        });

       
panel.add( new JLabel());
panel.add( new JLabel());
        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(emailLabel);
        panel.add(emailField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(confirmPasswordLabel);
        panel.add(confirmPasswordField);
        panel.add( new JLabel());
        panel.add(signUpButton);
        panel.add( new JLabel());
        panel.add( new JLabel());
      

         JLabel signupLabel = new JLabel("Déjà membre ?Connectez-vous ici !");
        Font linkFont = new Font("Arial", Font.BOLD , 14);
        signupLabel.setFont(linkFont);
        signupLabel.setForeground(Color.DARK_GRAY); // Couleur de lien
        signupLabel.setHorizontalAlignment(SwingConstants.CENTER);
        signupLabel.setBounds(200, 300, 400, 30);
        contentPane1.add(signupLabel);

        // Ajouter un gestionnaire d'événements pour le clic sur le lien d'inscription
        signupLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
               dispose();
                 Login loginWindow = new Login();
                loginWindow.display();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                // Changer la couleur du lien lorsqu'il est survolé
                signupLabel.setForeground(lightGreen );
            }

            @Override
            public void mouseExited(MouseEvent e) {
                // Revenir à la couleur de lien normale lorsque la souris quitte le lien
                signupLabel.setForeground(Color.DARK_GRAY);
            }
        });
        
        
        contentPane1.add(messageLabel, BorderLayout.CENTER);

        contentPane1.add(panel, BorderLayout.CENTER);
       contentPane1.add( signupLabel, BorderLayout.CENTER);


        contentPane1.setPreferredSize(new Dimension(550, 350)); // Adjust the dimensions as needed

        contentPane.add(contentPane1, BorderLayout.CENTER);


         
        // Afficher la fenêtre
        setLocationRelativeTo(null); // Centrer la fenêtre
        setVisible(true);
    
}
 public void display() {
        setVisible(true);
    }
 
 
  public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Signin());
    }
}

