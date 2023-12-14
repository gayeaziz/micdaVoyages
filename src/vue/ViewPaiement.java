package vue;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
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
import javax.swing.DefaultCellEditor;
import javax.swing.JTextField;

import entite.Paiement;
import serviceImplementation.PaiementServiceImpl;

public class ViewPaiement extends JFrame {

    private JTextField textCodePaiement;
    private JTextField textMethodePaiement;

    private JTable tablePaiements;
    private DefaultTableModel tableModel;
    private List<Paiement> paiementList;

    private JButton btnAdd;
    private JButton btnUpdate;

    public ViewPaiement() {
        setTitle("Gestion des Paiements");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

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
        panel.setBorder(new TitledBorder("Ajouter un Paiement"));

        JLabel labelCodePaiement = new JLabel("CodePaiement : ");
        textCodePaiement = new JTextField(20);

        JLabel labelMethodePaiement = new JLabel("MethodePaiement : ");
        textMethodePaiement = new JTextField(20);

        btnAdd = new JButton("Ajouter");
        btnUpdate = new JButton("Modifier");

        btnAdd.addActionListener(e -> addPaiement());
        btnUpdate.addActionListener(e -> updatePaiement());

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(btnAdd);
        buttonPanel.add(btnUpdate);

        panel.setLayout(new BorderLayout());
        panel.add(labelCodePaiement, BorderLayout.WEST);
        panel.add(textCodePaiement, BorderLayout.CENTER);
        panel.add(labelMethodePaiement, BorderLayout.WEST);
        panel.add(textMethodePaiement, BorderLayout.CENTER);
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
        paiementService.savePaiement(paiement);

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
    private class ButtonRenderer extends JButton implements TableCellRenderer {
        public ButtonRenderer() {
            setOpaque(true);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            setText((value == null) ? "" : value.toString());
            return this;
        }
    }

    // Classe ButtonEditor pour gérer les actions lorsqu'un bouton est cliqué dans la colonne "Actions"
    private class ButtonEditor extends DefaultCellEditor {
        private JButton button;

        public ButtonEditor() {
            super(new JTextField());
            setClickCountToStart(1);

            button = new JButton("Modifier");
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Ajoutez ici le code à exécuter lorsque le bouton "Modifier" est cliqué
                }
            });
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            return button;
        }
    }

    public static void main(String[] args) {
        new ViewPaiement().setVisible(true);
    }
}
