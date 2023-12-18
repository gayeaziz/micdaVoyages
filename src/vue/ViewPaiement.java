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
import javax.swing.JFrame;
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
import entite.Paiement;
import serviceImplementation.PaiementServiceImpl;

public class ViewPaiement extends JPanel{

    private JTextField textCodePaiement;
    private JTextField textMethodePaiement;

    private JTable tablePaiements;
    private DefaultTableModel tableModel;
    private List<Paiement> paiementList;

    private JButton btnAdd;
    private JButton btnUpdate;

    public ViewPaiement() {
//        setTitle("Gestion des Paiements");
//        setSize(800, 600);
//        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
      
        
        //...

        

        // Formulaire
        JPanel addPanel = createAddPanel();
        add(addPanel, BorderLayout.NORTH);

        // Tableau
        String[] headers = {"CodePaiement", "MethodePaiement", "Actions"};
        tableModel = new DefaultTableModel(headers, 0);
        tablePaiements = new JTable(tableModel);

        // Ajouter un rendu personnalisé pour la colonne "Actions"
        tablePaiements.getColumnModel().getColumn(2).setCellRenderer(new ButtonRenderer());
        // Ajouter un éditeur personnalisé pour la colonne "Actions"
        tablePaiements.getColumnModel().getColumn(2).setCellEditor(new ButtonEditor());

        add(new JScrollPane(tablePaiements), BorderLayout.CENTER);

        fetchAndDisplayPaiements();
    }

    private JPanel createAddPanel() {
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(700, 200));
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        panel.setBorder(new TitledBorder("GESTION  PAIEMENT"));
        //Ajout du titre descriptif
        JLabel titleLabel = new JLabel("GESTION  PAIEMENT");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18)); // Ajustez la taille de la police si nécessaire
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));
        titlePanel.add(Box.createVerticalStrut(10));
        titlePanel.add(titleLabel);
        titlePanel.add(Box.createVerticalStrut(10));
        
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(titlePanel);
        
        JPanel labelCodePaiement = new JPanel();
        labelCodePaiement.add(new JLabel("CodePaiement : "));
        textCodePaiement = new JTextField(46);
        labelCodePaiement.add(textCodePaiement);

        JPanel labelMethodePaiement = new JPanel();
        labelMethodePaiement.add(new JLabel("MethodePaiement : "));
        textMethodePaiement = new JTextField(46);
        labelMethodePaiement.add(textMethodePaiement);

        btnAdd = new JButton("Ajouter");
        btnUpdate = new JButton("Modifier");


        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	addPaiement();
            }
        });

        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	updatePaiement();
            }
        });
 
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(btnAdd);
        buttonPanel.add(btnUpdate);

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(labelCodePaiement);
        panel.add(Box.createVerticalStrut(10));
        panel.add(labelMethodePaiement);
        panel.add(Box.createVerticalStrut(10));
        panel.add(buttonPanel, BorderLayout.SOUTH);
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));

        return panel;
    }

    private void addPaiement() {
        // Récupérer les données depuis les champs de texte
        String codePaiement = textCodePaiement.getText();
        String methodePaiement = textMethodePaiement.getText();

        // Ajouter le paiement
        Paiement paiement = new Paiement(codePaiement, methodePaiement, 0);
        PaiementServiceImpl paiementService = new PaiementServiceImpl();
        paiementService.addPaiement(paiement);

        // Rafraîchir le tableau des paiements
        fetchAndDisplayPaiements();

        // Réinitialiser les champs de texte après l'ajout
        textCodePaiement.setText("");
        textMethodePaiement.setText("");
    }

    private void updatePaiement() {
        // Récupérer les données depuis les champs de texte
        String codePaiement = textCodePaiement.getText();
        String methodePaiement = textMethodePaiement.getText();

        // Récupérer la ligne sélectionnée dans le tableau
        int selectedRow = tablePaiements.getSelectedRow();
        if (selectedRow == -1) {
            // Aucune ligne sélectionnée, rien à mettre à jour
            return;
        }

        // Récupérer le paiement correspondant à la ligne sélectionnée
        Paiement selectedPaiement = paiementList.get(selectedRow);

        // Mettre à jour les données du paiement
        selectedPaiement.setCodePaiement(codePaiement);
        selectedPaiement.setMethodePaiement(methodePaiement);

        // Utiliser le service pour mettre à jour le paiement dans la base de données
        PaiementServiceImpl paiementService = new PaiementServiceImpl();
        paiementService.updatePaiement(selectedPaiement);

        // Rafraîchir le tableau des paiements
        fetchAndDisplayPaiements();

        // Réinitialiser les champs de texte après la mise à jour
        textCodePaiement.setText("");
        textMethodePaiement.setText("");
    }

    private void fetchAndDisplayPaiements() {
        // Utiliser le service pour récupérer la liste des paiements depuis la base de données
        PaiementServiceImpl paiementService = new PaiementServiceImpl();
        paiementList = paiementService.getAllPaiements();

        // Effacer le tableau actuel
        tableModel.setRowCount(0);

        // Ajouter les paiements à la table avec des boutons "Modifier" et "Supprimer"
        for (Paiement paiement : paiementList) {
            tableModel.addRow(new Object[]{paiement.getCodePaiement(), paiement.getMethodePaiement(), paiement});
        }
    }

    // Classe ButtonRenderer pour rendre correctement les boutons dans la colonne "Actions"
//    private class ButtonRenderer extends JButton implements TableCellRenderer {
//        public ButtonRenderer() {
//            setOpaque(true);
//        }
    private class ButtonRenderer implements TableCellRenderer {
        private JPanel panel;
        private JButton btnEdit;
        private JButton btnDelete;
        
        public ButtonRenderer() {
            panel = new JPanel();
            panel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
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
//    private class ButtonEditor extends DefaultCellEditor {
//        private JButton button;
//
//        public ButtonEditor() {
//            super(new JTextField());
//            setClickCountToStart(1);
//
//            button = new JButton("Modifier");
//            button.addActionListener(new ActionListener() {
//                @Override
//                public void actionPerformed(ActionEvent e) {
//                    // Ajoutez ici le code à exécuter lorsque le bouton "Modifier" est cliqué
//                }
//            });
//        }
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
                    int selectedRow = tablePaiements.getSelectedRow();
                    if (selectedRow != -1 && selectedRow <paiementList.size()) {
                        Paiement selectedPaiement = paiementList.get(selectedRow);

                        textCodePaiement.setText(selectedPaiement.getCodePaiement());
                        textMethodePaiement.setText(selectedPaiement.getMethodePaiement());
                       

                        btnAdd.setEnabled(false);
                        btnUpdate.setEnabled(true);
                    }
                }
            });

            btnDelete.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.println("Supprimer : " + clickedValue);
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
    public static void main(String[] args) {

        ViewPaiement view = new ViewPaiement();
        view.setSize(700, 500);
        view.setVisible(true);

    }
//    public static void main(String[] args) {
//        new ViewPaiement().setVisible(true);
//    }
}
