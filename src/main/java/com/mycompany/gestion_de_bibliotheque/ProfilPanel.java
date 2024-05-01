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
public class ProfilPanel extends JPanel {
    public ProfilPanel() {
        setLayout(new BorderLayout());

        // Placeholder content for LibraryPanel
        JTextArea libraryContent = new JTextArea("Welcome to the profile!\n\n" );
               
               
        libraryContent.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(libraryContent);

        add(scrollPane, BorderLayout.CENTER);
    }
}
