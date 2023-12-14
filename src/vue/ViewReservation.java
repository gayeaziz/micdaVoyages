package vue;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;


import entite.Bus;
import entite.Client;
import entite.Reservation;
import entite.Trajet;
import service.ServiceReservation;
import serviceImplementation.ReservationServiceImpl;

public class ViewReservation extends JFrame {

    private JTextField textNumeroSiege;
    private JTextField textPaye;
    private JTextField textClient;
    private JTextField textTrajet;
    private JTable tableReservations;
    private DefaultTableModel tableModel;
    private List<Reservation> reservationList;
    
    private JButton btnAdd;
    private JButton btnUpdate;

    public ViewReservation() {
        setVisible(true);
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Formulaire
        JPanel addPanel = createAddPanel();
        add(addPanel, BorderLayout.NORTH);

        // Tableau
        String[] headers = {"Trajet ID", "Client ID", "Numéro de Siège", "Payé", "Actions"};
        tableModel = new DefaultTableModel(headers, 0);
        tableReservations = new JTable(tableModel);

        // Ajouter un rendu personnalisé pour la colonne "Actions"
        tableReservations.getColumnModel().getColumn(4).setCellRenderer(new ButtonRenderer());
        // Ajouter un éditeur personnalisé pour la colonne "Actions"
        tableReservations.getColumnModel().getColumn(4).setCellEditor(new ButtonEditor());

        add(new JScrollPane(tableReservations), BorderLayout.CENTER);

        fetchAndDisplayReservations();
    }

    private void fetchAndDisplayReservations() {
        // Utiliser le service pour récupérer la liste des réservations depuis la base de données
        ServiceReservation reservationService = new ReservationServiceImpl();
        List<Reservation> reservationList = reservationService.getAllReservations();

        // Effacer le tableau actuel
        tableModel.setRowCount(0);

        // Ajouter les réservations à la table avec des boutons "Modifier" et "Supprimer"
        for (Reservation reservation : reservationList) {
            tableModel.addRow(new Object[]{reservation.getTrajet().getTrajetId(),
                    reservation.getClient().getClientId(), reservation.getNumeroSiege(),
                    reservation.isPaye(), reservation});
        }
        // Désactiver le bouton "Ajouter" lors de la mise à jour
        btnAdd.setEnabled(true);
    }

    private JPanel createAddPanel() {
        JPanel panel = new JPanel();
        panel.setBorder(new TitledBorder("Ajouter une Réservation"));

        JPanel row1 = new JPanel();
        row1.add(new JLabel("Numéro de Siège : "));
        textNumeroSiege = new JTextField(40);
        row1.add(textNumeroSiege);

        JPanel row2 = new JPanel();
        row2.add(new JLabel("Payé : "));
        textPaye = new JTextField(46);
        row2.add(textPaye);

        JPanel row3 = new JPanel();
        row3.add(new JLabel("Trajet : "));
        textTrajet = new JTextField(46);
        row3.add(textTrajet);
        
        JPanel row4 = new JPanel();
        row4.add(new JLabel("Client : "));
        textClient = new JTextField(46);
        row4.add(textClient);
        
        btnAdd = new JButton("Ajouter");
        btnUpdate = new JButton("Modifier");

        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addReservation();
            }
        });

        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateReservation();
            }
        });

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(btnAdd);
        buttonPanel.add(btnUpdate);

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        panel.add(row1);
        panel.add(Box.createVerticalStrut(10));
        panel.add(row2);
        panel.add(Box.createVerticalStrut(20));
        panel.add(row3);
        panel.add(Box.createVerticalStrut(10));
        panel.add(row4);
        panel.add(Box.createVerticalStrut(10));
        panel.add(buttonPanel);

        panel.setBorder(new EmptyBorder(10, 10, 10, 10));

        // Activer le bouton Ajouter par défaut
        btnAdd.setEnabled(true);

        return panel;
    }

    private void addReservation() {
        // Récupérer les données depuis les champs de texte
        int numeroSiege = Integer.parseInt(textNumeroSiege.getText());
        boolean paye = Boolean.parseBoolean(textPaye.getText());

        // Créer un nouveau trajet et un nouveau client (pour les fins de démonstration)
        Trajet trajet = new Trajet(1L, new Bus(), "Ville1", "Ville2", "2023-01-01", "12:00", 30);
        Client client = new Client(1L, "John", "Doe", "123456789", "1234567890", "CODE123");

        // Créer une nouvelle réservation
        Reservation newReservation = new Reservation(null, trajet, client, numeroSiege, paye);

        // Utiliser le service pour sauvegarder la nouvelle réservation dans la base de données
        ReservationServiceImpl reservationService = new ReservationServiceImpl();
        reservationService.saveReservation(newReservation);

        // Rafraîchir le tableau des réservations
        fetchAndDisplayReservations();

        // Réinitialiser les champs de texte après l'ajout
        textNumeroSiege.setText("");
        textPaye.setText("");
    }

    private void updateReservation() {
        // Récupérer les données depuis les champs de texte
        int numeroSiege = Integer.parseInt(textNumeroSiege.getText());
        boolean paye = Boolean.parseBoolean(textPaye.getText());

        // Récupérer la ligne sélectionnée dans le tableau
        int selectedRow = tableReservations.getSelectedRow();
        if (selectedRow == -1 || selectedRow >= reservationList.size()) {
            // Aucune ligne sélectionnée ou index hors limites, rien à mettre à jour
            return;
        }

        // Récupérer la réservation correspondant à la ligne sélectionnée
        Reservation selectedReservation = reservationList.get(selectedRow);

        // Mettre à jour les données de la réservation
        selectedReservation.setNumeroSiege(numeroSiege);
        selectedReservation.setPaye(paye);

        // Utiliser le service pour mettre à jour la réservation dans la base de données
        ReservationServiceImpl reservationService = new ReservationServiceImpl();
        reservationService.updateReservation(selectedReservation);

        // Rafraîchir le tableau des réservations
        fetchAndDisplayReservations();

        // Réinitialiser les champs de texte après la mise à jour
        textNumeroSiege.setText("");
        textPaye.setText("");

        // Activer le bouton "Ajouter" après la mise à jour
        btnAdd.setEnabled(true);
        // Désactiver le bouton "Modifier" après la mise à jour
        btnUpdate.setEnabled(true);
    }

    public static void main(String[] args) {
        new ViewReservation().setVisible(true);
    }
}
