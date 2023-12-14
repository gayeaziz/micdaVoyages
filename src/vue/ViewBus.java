package vue;
//import java.awt.BorderLayout;
//import java.awt.Color;
//import java.awt.Component;
//import java.awt.FlowLayout;
//import java.awt.Font;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.util.List;
//
//import javax.swing.Box;
//import javax.swing.BoxLayout;
//import javax.swing.DefaultCellEditor;
//import javax.swing.JButton;
//import javax.swing.JFrame;
//import javax.swing.JLabel;
//import javax.swing.JPanel;
//import javax.swing.JScrollPane;
//import javax.swing.JTable;
//import javax.swing.JTextField;
//import javax.swing.border.EmptyBorder;
//import javax.swing.border.TitledBorder;
//import javax.swing.table.DefaultTableModel;
//import javax.swing.table.TableCellRenderer;
//
//import entite.Bus;
//import serviceImplementation.BusServiceImpl;
//
//public class ViewBus extends JFrame {
//    private JTextField textNom;
//    private JTextField textDescription;
//    private JTextField textStatus;
//
//    private JTable tableBuses;
//    private DefaultTableModel tableModel;
//    private List<Bus> busList;
//
//    public ViewBus() {
//        setTitle("Gestion des bus");
//        setSize(800, 600);
//        setDefaultCloseOperation(EXIT_ON_CLOSE);
//        setLayout(new BorderLayout());
//
//        // Formulaire
//        JPanel addPanel = createAddPanel();
//        add(addPanel, BorderLayout.NORTH);
//
//        // Tableau
//        String[] headers = {"Nom", "Description", "Statut", "Actions"};
//        tableModel = new DefaultTableModel(headers, 0);
//        tableBuses = new JTable(tableModel);
//
//        // Ajouter un rendu personnalisé pour la colonne "Actions"
//        tableBuses.getColumnModel().getColumn(3).setCellRenderer(new ButtonRenderer());
//        // Ajouter un éditeur personnalisé pour la colonne "Actions"
//        tableBuses.getColumnModel().getColumn(3).setCellEditor(new ButtonEditor());
//
//        add(new JScrollPane(tableBuses), BorderLayout.CENTER);
//
//        fetchAndDisplayBuses();
//    }
//
//    private JPanel createAddPanel() {
//        JPanel panel = new JPanel();
//        panel.setBorder(new TitledBorder("Ajouter un bus"));
//
//        JPanel row1 = new JPanel();
//        row1.add(new JLabel("Nom : "));
//        textNom = new JTextField(50);
//        row1.add(textNom);
//
//        JPanel row2 = new JPanel();
//        row2.add(new JLabel("Description : "));
//        textDescription = new JTextField(46);
//        row2.add(textDescription);
//
//        JPanel row3 = new JPanel();
//        row3.add(new JLabel("Statut : "));
//        textStatus = new JTextField(50);
//        row3.add(textStatus);
//
//        JButton btnAdd = new JButton("Ajouter");
//
//        btnAdd.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                addBus();
//            }
//        });
//
//        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
//        panel.add(row1);
//
//        panel.add(Box.createVerticalStrut(10));
//        panel.add(row2);
//
//        panel.add(Box.createVerticalStrut(10));
//        panel.add(row3);
//
//        panel.add(Box.createVerticalStrut(20));
//        panel.add(btnAdd);
//
//        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
//        panel.setAlignmentX(CENTER_ALIGNMENT);
//
//        return panel;
//    }
//
//    private void addBus() {
//        // Récupérer les données depuis les champs de texte
//        String nom = textNom.getText();
//        String description = textDescription.getText();
//        String etat = textStatus.getText();
//
//        // Créer un nouvel objet Bus
//        Bus newBus = new Bus();
//        newBus.setNom(nom);
//        newBus.setDescription(description);
//        newBus.setEtat(etat);
//
//        // Utiliser le service pour insérer le nouveau bus dans la base de données
//        BusServiceImpl busService = new BusServiceImpl();
//        busService.saveBus(newBus);
//
//        // Rafraîchir le tableau des bus
//        fetchAndDisplayBuses();
//
//        // Réinitialiser les champs de texte après l'ajout
//        textNom.setText("");
//        textDescription.setText("");
//        textStatus.setText("");
//    }
//
//    private void fetchAndDisplayBuses() {
//        // Utiliser le service pour récupérer la liste des bus depuis la base de données
//        BusServiceImpl busService = new BusServiceImpl();
//        busList = busService.getAllBuses();
//
//        // Effacer le tableau actuel
//        tableModel.setRowCount(0);
//
//        // Ajouter les bus à la table avec des boutons "Modifier" et "Supprimer"
//        for (Bus bus : busList) {
//            tableModel.addRow(new Object[]{bus.getNom(), bus.getDescription(), bus.getEtat(), bus});
//        }
//    }
//
//    public static void main(String[] args) {
//        new ViewBus().setVisible(true);
//    }
//
//    // Classe ButtonRenderer pour rendre correctement les boutons dans la colonne "Actions"
//    private class ButtonRenderer implements TableCellRenderer {
//        private JPanel panel;
//        private JButton btnEdit;
//        private JButton btnDelete;
//
//        public ButtonRenderer() {
//            panel = new JPanel();
//            panel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
////           
//         // Utilisation de la balise <html> pour le centrage du texte
//            btnEdit = new JButton("<html><center>Modifier</center></html>");
//            btnDelete = new JButton("<html><center>Supprimer</center></html>");
//
//            // Ajout d'une marge en haut aux boutons
//            int topMargin = -3; // Vous pouvez ajuster la marge ici
//            btnEdit.setBorder(new EmptyBorder(topMargin, 20, 20, 20));
//            btnDelete.setBorder(new EmptyBorder(topMargin, 20, 20, 20));
//            
//            // Définir la couleur d'arrière-plan et de police pour chaque bouton
//            btnEdit.setBackground(new Color(51, 153, 255)); // Couleur de fond bleue
//            btnDelete.setBackground(new Color(255, 102, 102)); // Couleur de fond rouge
//
//            btnEdit.setForeground(Color.WHITE); // Couleur de police blanche
//            btnDelete.setForeground(Color.WHITE); // Couleur de police blanche
// 
//
//            panel.add(btnEdit);
//            panel.add(btnDelete);
//        }
//
//        @Override
//        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
//            return panel;
//        }
//    }
//
//    // Classe ButtonEditor pour gérer les actions lorsqu'un bouton est cliqué dans la colonne "Actions"
//    private class ButtonEditor extends DefaultCellEditor {
//        private JPanel panel;
//        private JButton btnEdit;
//        private JButton btnDelete;
//        private Object clickedValue;
//
//        public ButtonEditor() {
//            super(new JTextField());
//
//            panel = new JPanel();
//            panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 0));
//
//            btnEdit = new JButton("Modifier");
//            btnDelete = new JButton("Supprimer");
//
//            btnEdit.addActionListener(new ActionListener() {
//                @Override
//                public void actionPerformed(ActionEvent e) {
//                    // Action pour le bouton "Modifier"
//                    System.out.println("Modifier : " + clickedValue);
//                }
//            });
//
//            btnDelete.addActionListener(new ActionListener() {
//                @Override
//                public void actionPerformed(ActionEvent e) {
//                    // Action pour le bouton "Supprimer"
//                    System.out.println("Supprimer : " + clickedValue);
//                }
//            });
//
//            panel.add(btnEdit);
//            panel.add(btnDelete);
//        }
//
//        public Object getCellEditorValue() {
//            return clickedValue;
//        }
//
//        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
//            clickedValue = value;
//            return panel;
//        }
//    }
//}



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
import javax.swing.table.TableCellRenderer;
import entite.Bus;
import serviceImplementation.BusServiceImpl;

public class ViewBus extends JFrame {
    private JTextField textNom;
    private JTextField textDescription;
    private JTextField textStatus;

    private JTable tableBuses;
    private DefaultTableModel tableModel;
    private List<Bus> busList;

    private JButton btnAdd;
    private JButton btnUpdate;

    public ViewBus() {
        setTitle("Gestion des bus");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Formulaire
        JPanel addPanel = createAddPanel();
        add(addPanel, BorderLayout.NORTH);

        // Tableau
        String[] headers = {"Nom", "Description", "Statut", "Actions"};
        tableModel = new DefaultTableModel(headers, 0);
        tableBuses = new JTable(tableModel);

        // Ajouter un rendu personnalisé pour la colonne "Actions"
        tableBuses.getColumnModel().getColumn(3).setCellRenderer(new ButtonRenderer());
        // Ajouter un éditeur personnalisé pour la colonne "Actions"
        tableBuses.getColumnModel().getColumn(3).setCellEditor(new ButtonEditor());

        add(new JScrollPane(tableBuses), BorderLayout.CENTER);

        fetchAndDisplayBuses();
    }

    private JPanel createAddPanel() {
        JPanel panel = new JPanel();
        panel.setBorder(new TitledBorder("Ajouter un bus"));

        JPanel row1 = new JPanel();
        row1.add(new JLabel("Nom : "));
        textNom = new JTextField(50);
        row1.add(textNom);

        JPanel row2 = new JPanel();
        row2.add(new JLabel("Description : "));
        textDescription = new JTextField(46);
        row2.add(textDescription);

        JPanel row3 = new JPanel();
        row3.add(new JLabel("Statut : "));
        textStatus = new JTextField(50);
        row3.add(textStatus);

        btnAdd = new JButton("Ajouter");
        btnUpdate = new JButton("Modifier"); // Nouveau bouton Modifier

        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addBus();
            }
        });

//        btnUpdate.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                updateBus();
//            }
//        });
        
        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Récupérer les données depuis les champs de texte
                String nom = textNom.getText();
                String description = textDescription.getText();
                String etat = textStatus.getText();

                // Récupérer la ligne sélectionnée dans le tableau
                int selectedRow = tableBuses.getSelectedRow();
                if (selectedRow == -1) {
                    // Aucune ligne sélectionnée, rien à mettre à jour
                    return;
                }

                // Récupérer le bus correspondant à la ligne sélectionnée
                Bus selectedBus = busList.get(selectedRow);

                // Mettre à jour les données du bus
                selectedBus.setNom(nom);
                selectedBus.setDescription(description);
                selectedBus.setEtat(etat);
                

                // Utiliser le service pour mettre à jour le bus dans la base de données
                BusServiceImpl busService = new BusServiceImpl();
//                busService.updateBus(selectedBus);
                System.out.println("Avant mise à jour : " + selectedBus);
                busService.updateBus(selectedBus);
                System.out.println("Après mise à jour : " + selectedBus);
                


                // Rafraîchir le tableau des bus
                fetchAndDisplayBuses();

                // Réinitialiser les champs de texte après la mise à jour
                textNom.setText("");
                textDescription.setText("");
                textStatus.setText("");

                // Activer le bouton "Ajouter" après la mise à jour
                btnAdd.setEnabled(true);
                // Désactiver le bouton "Enregistrer les modifications" après la mise à jour
                btnUpdate.setEnabled(false);
            }
        });


//        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
//        panel.add(row1);
//
//        panel.add(Box.createVerticalStrut(10));
//        panel.add(row2);
//
//        panel.add(Box.createVerticalStrut(10));
//        panel.add(row3);
//
//        panel.add(Box.createVerticalStrut(20));
//        panel.add(btnAdd);
//        panel.add(btnUpdate); // Ajout du bouton Modifier à votre panneau
//
//        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
//        panel.setAlignmentX(CENTER_ALIGNMENT);
//
//        // Activer le bouton Ajouter par défaut
//        btnAdd.setEnabled(true);
//
//        return panel;
        // Utiliser un FlowLayout avec une disposition horizontale spécifique
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
        panel.add(buttonPanel); // Ajout du panneau de boutons à votre panneau

        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        panel.setAlignmentX(CENTER_ALIGNMENT);

        // Activer le bouton Ajouter par défaut
        btnAdd.setEnabled(true);

        return panel;
    }

    private void addBus() {
        // Récupérer les données depuis les champs de texte
        String nom = textNom.getText();
        String description = textDescription.getText();
        String etat = textStatus.getText();

        // Créer un nouvel objet Bus
        Bus newBus = new Bus();
        newBus.setNom(nom);
        newBus.setDescription(description);
        newBus.setEtat(etat);

        // Utiliser le service pour insérer le nouveau bus dans la base de données
        BusServiceImpl busService = new BusServiceImpl();
        busService.saveBus(newBus);

        // Rafraîchir le tableau des bus
        fetchAndDisplayBuses();

        // Réinitialiser les champs de texte après l'ajout
        textNom.setText("");
        textDescription.setText("");
        textStatus.setText("");
    }

    private void updateBus() {
        // Récupérer les données depuis les champs de texte
        String nom = textNom.getText();
        String description = textDescription.getText();
        String etat = textStatus.getText();

        // Récupérer la ligne sélectionnée dans le tableau
        int selectedRow = tableBuses.getSelectedRow();
        if (selectedRow == -1) {
            // Aucune ligne sélectionnée, rien à mettre à jour
            return;
        }

        // Récupérer le bus correspondant à la ligne sélectionnée
        Bus selectedBus = busList.get(selectedRow);

        // Mettre à jour les données du bus
        selectedBus.setNom(nom);
        selectedBus.setDescription(description);
        selectedBus.setEtat(etat);

        // Utiliser le service pour mettre à jour le bus dans la base de données
        BusServiceImpl busService = new BusServiceImpl();
        busService.updateBus(selectedBus);

        // Rafraîchir le tableau des bus
        fetchAndDisplayBuses();

        // Réinitialiser les champs de texte après la mise à jour
        textNom.setText("");
        textDescription.setText("");
        textStatus.setText("");
        btnAdd.setEnabled(true);
    }

    private void fetchAndDisplayBuses() {
        // Utiliser le service pour récupérer la liste des bus depuis la base de données
        BusServiceImpl busService = new BusServiceImpl();
        busList = busService.getAllBuses();

        // Effacer le tableau actuel
        tableModel.setRowCount(0);

        // Ajouter les bus à la table avec des boutons "Modifier" et "Supprimer"
        for (Bus bus : busList) {
            tableModel.addRow(new Object[]{bus.getNom(), bus.getDescription(), bus.getEtat(), bus});
        }
        // Désactiver le bouton "Ajouter" lors de la mise à jour
        btnAdd.setEnabled(true);
    }

    public static void main(String[] args) {
        new ViewBus().setVisible(true);
    }

    // Classe ButtonRenderer pour rendre correctement les boutons dans la colonne "Actions"
    private class ButtonRenderer implements TableCellRenderer {
        private JPanel panel;
        private JButton btnEdit;
        private JButton btnDelete;

        public ButtonRenderer() {
            panel = new JPanel();
            panel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
            // Utilisation de la balise <html> pour le centrage du texte
            btnEdit = new JButton("<html><center>Modifier</center></html>");
            btnDelete = new JButton("<html><center>Supprimer</center></html>");
            btnEdit.setPreferredSize(new Dimension(90, 15));
            btnDelete.setPreferredSize(new Dimension(90, 15));

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
                        Bus selectedBus = busList.get(selectedRow);

                        // Afficher les informations du bus dans les champs de texte
                        textNom.setText(selectedBus.getNom());
                        textDescription.setText(selectedBus.getDescription());
                        textStatus.setText(selectedBus.getEtat());
                     
                        // Désactiver le bouton "Ajouter" pendant la modification
                        btnAdd.setEnabled(false);

                        // Activer un autre bouton (par exemple, "Enregistrer les modifications") si nécessaire
                        btnUpdate.setEnabled(true);
                    }
                }
            });

//            btnEdit.addActionListener(new ActionListener() {
//
//                @Override
//                public void actionPerformed(ActionEvent e) {
//                    
//                    int selectedRow = tableBuses.getSelectedRow();
//                    Bus selectedBus = busList.get(selectedRow);
//
//                    textNom.setText(selectedBus.getNom());
//                    textDescription.setText(selectedBus.getDescription());
//                    textStatus.setText(selectedBus.getEtat());
//
//                    selectedBus.setNom(textNom.getText());
//                    selectedBus.setDescription(textDescription.getText());
//                    selectedBus.setEtat(textStatus.getText());
//                    
//                    BusServiceImpl busService = new BusServiceImpl();
//                    busService.updateBus(selectedBus);
//                    
//                    fetchAndDisplayBuses();
//                }
//            });
            btnDelete.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Action pour le bouton "Supprimer"
                    System.out.println("Supprimer : " + clickedValue);
                }
            });

            panel.add(btnEdit);
            panel.add(btnDelete);
        }

        public Object getCellEditorValue() {
            return clickedValue;
        }
        private int selectedRow;

        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            clickedValue = value;
            selectedRow = row; 
            return panel;
        }
    }
}

