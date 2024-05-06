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

        // Retrieve available books from the library
        List<Livre> livresDisponibles = bibliotheque.getLivresDisponibles();

        // Create the table with book data
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

        // Borrow button
        emprunterButton = new JButton("Emprunter");
        emprunterButton.addActionListener(e -> {
            // Retrieve selected books
            int[] selectedRows = livresTable.getSelectedRows();
            for (int row : selectedRows) {
                Livre livre = livresDisponibles.get(row);
                // Example action: borrow the book via the library
                User user = new User("username", "role", "password","email"); // Example User object
                bibliotheque.emprunterLivre(livre, user, new Date(), new Date()); // Example borrow and return dates
                // Update availability in the interface after borrowing
                tableModel.removeRow(row);
            }
            // Example: display borrow and return dates
            JOptionPane.showMessageDialog(null, "Livres empruntés avec succès !");
        });

        add(emprunterButton, BorderLayout.SOUTH);
    }
}
