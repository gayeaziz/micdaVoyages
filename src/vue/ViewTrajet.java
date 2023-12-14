package vue;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultCellEditor;
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

import entite.Trajet;
import serviceImplementation.TrajetServiceImpl;

public class ViewTrajet extends JFrame {

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
        setVisible(true);
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
        tableBuses.getColumnModel().getColumn(5).setCellEditor((TableCellEditor) new ButtonEditor());

        add(new JScrollPane(tableBuses), BorderLayout.CENTER);

        fetchAndDisplayBuses();
    }

    private JPanel createAddPanel() {
        JPanel panel = new JPanel();
        panel.setBorder(new TitledBorder("Ajouter un bus"));

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
                addBus();
            }
        });

        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateBus();
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

    private void addBus() {
        // Récupérer les données depuis les champs de texte
        String bus = textBus.getText();
        String villeDepart = textVilleDepart.getText();
        String dateDepart = textDateDepart.getText();
        String heureDepart = textHeureDepart.getText();
        String placesTotales = textPlacesTotales.getText();

        // Créer un nouvel objet Bus
        Trajet newTrajet = new Trajet((long) 0, bus, villeDepart, dateDepart, heureDepart, placesTotales, 0);

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

    private void updateBus() {
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
        selectedTrajet.setBus(bus);
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
        // Utiliser le service pour récupérer la liste des bus depuis la base de données
        TrajetServiceImpl trajetService = new TrajetServiceImpl();
        List<Trajet> trajetList = trajetService.getAllTrajetes();

        // Effacer le tableau actuel
        tableModel.setRowCount(0);

        // Ajouter les bus à la table avec des boutons "Modifier" et "Supprimer"
        for (Trajet trajet : trajetList) {
            tableModel.addRow(new Object[]{trajet.getBus(),
                    trajet.getVilleDepart(), trajet.getDateDepart(), trajet.getHeureDepart(),
                    trajet.getPlacesTotales(), trajet});
        }
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
            btnEdit = new JButton("<html><center>Modifier</center></html>");
            btnDelete = new JButton("<html><center>Supprimer</center></html>");

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
                        textBus.setText(selectedTrajet.getBus());
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

        public Object getCellEditorValue() {
            return clickedValue;
        }

        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            clickedValue = value;
            return panel;
        }
    }

    public static void main(String[] args) {
        new ViewTrajet().setVisible(true);
    }
}

