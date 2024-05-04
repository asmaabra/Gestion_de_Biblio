package com.mycompany.gestion_de_bibliotheque;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Date;
import java.util.List;

public class BiblioPanel extends JPanel {
    private JButton emprunterButton;
    private JTable livresTable;
    private DefaultTableModel tableModel;
    private Bibliotheque bibliotheque;

    public BiblioPanel(Bibliotheque bibliotheque) {
        this.bibliotheque = bibliotheque;

        setLayout(new BorderLayout());

        // Récupération des livres disponibles de la bibliothèque
        List<Livre> livresDisponibles = bibliotheque.getLivresDisponibles();

        // Création du tableau avec les données des livres
        String[] columnNames = {"Titre", "Auteur"};
        Object[][] rowData = new Object[livresDisponibles.size()][2];
        for (int i = 0; i < livresDisponibles.size(); i++) {
            Livre livre = livresDisponibles.get(i);
            rowData[i][0] = livre.getTitre();
            rowData[i][1] = livre.getAuteur();
        }
        tableModel = new DefaultTableModel(rowData, columnNames);
        livresTable = new JTable(tableModel);

        JScrollPane scrollPane = new JScrollPane(livresTable);
        add(scrollPane, BorderLayout.CENTER);

        // Bouton Emprunter
        emprunterButton = new JButton("Emprunter");
        emprunterButton.addActionListener(e -> {
            // Récupérer les livres sélectionnés
            int[] selectedRows = livresTable.getSelectedRows();
            for (int row : selectedRows) {
                Livre livre = livresDisponibles.get(row);
                // Exemple d'action : emprunter le livre via la bibliothèque
                User user = new User("username", "role", "password"); // Example User object
                bibliotheque.emprunterLivre(livre, user, new Date(), new Date()); // Exemple de dates d'emprunt et de retour
                // Mettre à jour la disponibilité dans l'interface après emprunt
                tableModel.removeRow(row);
            }
            // Exemple : affichage de la date d'emprunt et de retour
            JOptionPane.showMessageDialog(null, "Livres empruntés avec succès !");
        });

        add(emprunterButton, BorderLayout.SOUTH);
    }
}
