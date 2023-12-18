package vue;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import entite.Bus;
import entite.Trajet;
import serviceImplementation.TrajetServiceImpl;

public class ViewTrajet extends JPanel {

    private JTextField textBus;
    private JTextField textVilleDepart;
    private JTextField textDateDepart;
    private JTextField textHeureDepart;
    private JTextField textPlacesTotales;

    private JTable tableBuses;
    private DefaultTableModel tableModel;
    private List<Trajet> busList;

    private JButton btnAdd;
    private JButton btnUpdate;

    public ViewTrajet() {
//        setVisible(true);
//        setSize(800, 600);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Formulaire
        JPanel addPanel = createAddPanel();
        add(addPanel, BorderLayout.NORTH);

        // Tableau
        String[] headers = {"Nom Bus", "Ville Depart", "Date Depart", "Heure Depart", "Place Total", "Actions"};
        tableModel = new DefaultTableModel(headers, 0);
        tableBuses = new JTable(tableModel);

        // Ajouter un rendu personnalisé pour la colonne "Actions"
        tableBuses.getColumnModel().getColumn(5).setCellRenderer(new ButtonRenderer());
        // Ajouter un éditeur personnalisé pour la colonne "Actions"
        tableBuses.getColumnModel().getColumn(5).setCellEditor(new ButtonEditor());

        add(new JScrollPane(tableBuses), BorderLayout.CENTER);

        fetchAndDisplayBuses();
    }

    private void setDefaultCloseOperation(int exitOnClose) {
		// TODO Auto-generated method stub

	}

	private JPanel createAddPanel() {
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(720, 400));
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        panel.setBorder(new TitledBorder("GESTION DES TRAJETS"));
        
        // Ajout du titre descriptif
        JLabel titleLabel = new JLabel("GESTION DES TRAJETS");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18)); // Ajustez la taille de la police si nécessaire
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));
        titlePanel.add(Box.createVerticalStrut(10));
        titlePanel.add(titleLabel);
        titlePanel.add(Box.createVerticalStrut(10));

        // ...

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(titlePanel);

        JPanel row1 = new JPanel();
        row1.add(new JLabel("Bus : "));
        textBus = new JTextField(50);
        row1.add(textBus);

        JPanel row2 = new JPanel();
        row2.add(new JLabel("VilleDepart : "));
        textVilleDepart = new JTextField(46);
        row2.add(textVilleDepart);

        JPanel row3 = new JPanel();
        row3.add(new JLabel("DateDepart : "));
        textDateDepart = new JTextField(46);
        row3.add(textDateDepart);

        JPanel row4 = new JPanel();
        row4.add(new JLabel("HeureDepart : "));
        textHeureDepart = new JTextField(46);
        row4.add(textHeureDepart);

        JPanel row5 = new JPanel();
        row5.add(new JLabel("PlaceTotales : "));
        textPlacesTotales = new JTextField(46);
        row5.add(textPlacesTotales);

        btnAdd = new JButton("Ajouter");
        btnUpdate = new JButton("Modifier");

        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addBus(null);
            }
        });

        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateBus(null);
            }
        });

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(btnAdd);
        buttonPanel.add(btnUpdate);

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        panel.add(row1);
        panel.add(Box.createVerticalStrut(10));
        panel.add(row2);
        panel.add(Box.createVerticalStrut(10));
        panel.add(row3);
        panel.add(Box.createVerticalStrut(20));
        panel.add(row4);
        panel.add(Box.createVerticalStrut(20));
        panel.add(row5);
        panel.add(Box.createVerticalStrut(20));
        panel.add(buttonPanel);

        panel.setBorder(new EmptyBorder(10, 10, 10, 10));

        // Activer le bouton Ajouter par défaut
        btnAdd.setEnabled(true);

        return panel;
    }

    private void addBus(Bus busId) {
        // Récupérer les données depuis les champs de texte
        String bus = textBus.getText();
        String villeDepart = textVilleDepart.getText();
       String dateDepart = textDateDepart.getText();
        String heureDepart = textHeureDepart.getText();
        String placesTotales = textPlacesTotales.getText();

        // Créer un nouvel objet Bus
        Trajet newTrajet = new Trajet(0, busId, villeDepart, dateDepart, heureDepart, placesTotales, 0);

        // Utiliser le service pour insérer le nouveau bus dans la base de données
        TrajetServiceImpl trajetService = new TrajetServiceImpl();
        trajetService.saveTrajet(newTrajet);

        // Rafraîchir le tableau des bus
        fetchAndDisplayBuses();

        // Réinitialiser les champs de texte après l'ajout
        textBus.setText("");
        textVilleDepart.setText("");
        textDateDepart.setText("");
        textHeureDepart.setText("");
        textPlacesTotales.setText("");
    }

    private void updateBus(Bus busId) {
        // Récupérer les données depuis les champs de texte
        String bus = textBus.getText();
        String villeDepart = textVilleDepart.getText();
        String dateDepart = textDateDepart.getText();
        String heureDepart = textHeureDepart.getText();
        String placesTotales = textPlacesTotales.getText();

        // Récupérer la ligne sélectionnée dans le tableau
        int selectedRow = tableBuses.getSelectedRow();
        if (selectedRow == -1) {
            // Aucune ligne sélectionnée, rien à mettre à jour
            return;
        }

        // Récupérer le bus correspondant à la ligne sélectionnée
        Trajet selectedTrajet = busList.get(selectedRow);

        // Mettre à jour les données du bus
        selectedTrajet.setBusId(busId);
        selectedTrajet.setVilleDepart(villeDepart);
        selectedTrajet.setDateDepart(dateDepart);
        selectedTrajet.setHeureDepart(heureDepart);
        selectedTrajet.setPlacesTotales(placesTotales);

        // Utiliser le service pour mettre à jour le bus dans la base de données
        TrajetServiceImpl trajetService = new TrajetServiceImpl();
        trajetService.updateTrajet(selectedTrajet);

        // Rafraîchir le tableau des bus
        fetchAndDisplayBuses();

        // Réinitialiser les champs de texte après la mise à jour
        textBus.setText("");
        textVilleDepart.setText("");
        textDateDepart.setText("");
        textHeureDepart.setText("");
        textPlacesTotales.setText("");

        // Activer le bouton "Ajouter" après la mise à jour
        btnAdd.setEnabled(true);
        // Désactiver le bouton "Enregistrer les modifications" après la mise à jour
        btnUpdate.setEnabled(false);
    }

    private void fetchAndDisplayBuses() {
        // Utiliser le service pour récupérer la liste des trajet depuis la base de données
        TrajetServiceImpl trajetService = new TrajetServiceImpl();
        List<Trajet> trajetList = trajetService.getAllTrajetes();

        // Effacer le tableau actuel
        tableModel.setRowCount(0);

        // Ajouter les bus à la table avec des boutons "Modifier" et "Supprimer"
        for (Trajet trajet : trajetList) {
            tableModel.addRow(new Object[]{trajet.getBusId(),
                    trajet.getVilleDepart(), trajet.getDateDepart(), trajet.getHeureDepart(),
                    trajet.getPlacesTotales(), trajet});
        }
        // Mettre à jour busList
        busList = trajetList;
        // Désactiver le bouton "Ajouter" lors de la mise à jour
        btnAdd.setEnabled(true);
    }

    // Classe ButtonRenderer pour rendre correctement les boutons dans la colonne "Actions"
    private class ButtonRenderer implements TableCellRenderer {
        private JPanel panel;
        private JButton btnEdit;
        private JButton btnDelete;

        public ButtonRenderer() {
            panel = new JPanel();
            panel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
         // Reduisez la taille de la police pour les boutons
            Font buttonFont = new Font("Arial", Font.BOLD, 10);
            btnEdit = new JButton("<html><center style='text-align:center;'>Modifier</center></html>");
            btnEdit.setFont(buttonFont);
            
            btnDelete = new JButton("<html><center style='text-align:center;'>Supprimer</center></html>");
            btnDelete.setFont(buttonFont);
            btnEdit.setPreferredSize(new Dimension(55, 15));
            btnDelete.setPreferredSize(new Dimension(61, 15));

            panel.add(btnEdit);
            panel.add(btnDelete);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            return panel;
        }
    }

    // Classe ButtonEditor pour gérer les actions lorsqu'un bouton est cliqué dans la colonne "Actions"
    private class ButtonEditor extends DefaultCellEditor {
        private JPanel panel;
        private JButton btnEdit;
        private JButton btnDelete;
        private Object clickedValue;

        public ButtonEditor() {
            super(new JTextField());

            panel = new JPanel();
            panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 0));

            btnEdit = new JButton("Modifier");
            btnDelete = new JButton("Supprimer");

            btnEdit.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int selectedRow = tableBuses.getSelectedRow();
                    if (selectedRow != -1 && selectedRow < busList.size()) {
                        Trajet selectedTrajet = busList.get(selectedRow);

                        // Afficher les informations du bus dans les champs de texte
//                        textBus.setText(selectedTrajet.getBusId());
                        textVilleDepart.setText(selectedTrajet.getVilleDepart());
                        textDateDepart.setText(selectedTrajet.getDateDepart());
                        textHeureDepart.setText(selectedTrajet.getHeureDepart());
                        textPlacesTotales.setText(String.valueOf(selectedTrajet.getPlacesTotales()));

                        // Désactiver le bouton "Ajouter" pendant la modification
                        btnAdd.setEnabled(false);

                        // Activer un autre bouton (par exemple, "Enregistrer les modifications") si nécessaire
                        btnUpdate.setEnabled(true);
                    }
                }
            });

            btnDelete.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Implement logic to delete the selected item
                    int selectedRow = tableBuses.getSelectedRow();
                    if (selectedRow != -1 && selectedRow < busList.size()) {
                        Trajet selectedTrajet = busList.get(selectedRow);

                        // Use the service to delete the selected item from the database
                        TrajetServiceImpl trajetService = new TrajetServiceImpl();
                        trajetService.deleteTrajet(selectedTrajet.getTrajetId());

                        // Refresh the table after deletion
                        fetchAndDisplayBuses();
                    }
                }
            });

            panel.add(btnEdit);
            panel.add(btnDelete);
        }

        @Override
		public Object getCellEditorValue() {
            return clickedValue;
        }

        @Override
		public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            clickedValue = value;
            return panel;
        }
    }

//    public static void main(String[] args) {
//        new ViewTrajet().setVisible(true);
//    }
}
////
////import javax.swing.*;
////import javax.swing.table.*;
////
////import entite.Bus;
////import entite.Trajet;
////import serviceImplementation.BusServiceImpl;
////import serviceImplementation.TrajetServiceImpl;
////
////import java.awt.*;
////import java.awt.event.*;
////import java.util.List;
////
////public class ViewTrajet extends JPanel {
////
////  private JComboBox comboBus;
////  private JTextField textVilleDepart;
////  private JTextField textDateDepart;
////
////  private JTable tableBuses;
////  private DefaultTableModel tableModel;
////
////  private List<Trajet> trajetList;
////  private List<Bus> busList;
////
////  public ViewTrajet() {
////	  comboBus = new JComboBox();
////    fetchAndDisplayBuses();
////
////    // Formulaire
////    JPanel addPanel = createAddPanel();
////    add(addPanel, BorderLayout.NORTH);
////
////    // Tableau
////    String[] headers = {"Nom Bus", "Ville Depart", "Date Depart", "Actions"};
////
////    tableModel = new DefaultTableModel(headers, 0);
////    tableBuses = new JTable(tableModel);
////
////    // Reste du code
////  }
////
////
////  private void fetchAndDisplayBuses() {
////
////	  TrajetServiceImpl trajetService = new TrajetServiceImpl();
////    trajetList = trajetService.getAllTrajetes();
////
////     BusServiceImpl busService = new BusServiceImpl();
////    busList = busService.getAllBuses();
////
////
////    // Met à jour la combobox des bus
////    updateBusCombo();
////
////    // Rafraichit la table
////    updateTable();
////  }
////
////  private void updateBusCombo() {
////    DefaultComboBoxModel model = new DefaultComboBoxModel(busList.toArray());
////    comboBus.setModel(model);
////  }
////
////  private void updateTable() {
////    // Vide et remplit le tableau
////  }
////
////  private JPanel createAddPanel() {
////
////    JPanel row1 = new JPanel();
////    row1.add(new JLabel("Bus : "));
////
////    comboBus = new JComboBox();
////    comboBus.setEditable(true);
////    row1.add(comboBus);
////
////    // Reste du formulaire
////
////
////	return row1;
////  }
////
////  private void addTrajet() {
////    Bus selectedBus = (Bus)comboBus.getSelectedItem();
////
////    // Crée le trajet avec le bus sélectionné
////  }
////
////}
