package vue;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EventObject;
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
import javax.swing.event.CellEditorListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

import entite.Client;
import service.ServiceClient;
import serviceImplementation.ClientServiceImpl;

public class ViewClient extends JFrame {

    private JTextField textPrenom;
    private JTextField textNom;
    private JTextField textNumeroIdentite;
    private JTextField textTelephone;
    private JTextField textCodePaiement;

    private JTable tableClientes;
    private DefaultTableModel tableModel;
    private List<Client> clientList;

    private JButton btnAdd;
    private JButton btnUpdate;

    public ViewClient() {
        setVisible(true);
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Formulaire
        JPanel addPanel = createAddPanel();
        add(addPanel, BorderLayout.NORTH);

        // Tableau
        String[] headers = {"Prenom", "Nom", "NumeroIdentite", "Telephone", "CodePaiement", "Actions"};
        tableModel = new DefaultTableModel(headers, 0);
        tableClientes = new JTable(tableModel);

        // Ajouter un rendu personnalisé pour la colonne "Actions"
        tableClientes.getColumnModel().getColumn(5).setCellRenderer(new ButtonRenderer());
        // Ajouter un éditeur personnalisé pour la colonne "Actions"
        tableClientes.getColumnModel().getColumn(5).setCellEditor(new ButtonEditor());

        add(new JScrollPane(tableClientes), BorderLayout.CENTER);

        fetchAndDisplayClientes();
    }

    private void fetchAndDisplayClientes() {
        // Utiliser le service pour récupérer la liste des clients depuis la base de données
    	ServiceClient clientService = new ClientServiceImpl();
        List<Client> clientList = clientService.getAllClientes();

        // Effacer le tableau actuel
        tableModel.setRowCount(0);

        // Ajouter les clients à la table avec des boutons "Modifier" et "Supprimer"
        for (Client client : clientList) {
            tableModel.addRow(new Object[]{client.getPrenom(),
                    client.getNom(), client.getNumeroIdentite(), client.getTelephone(),
                    client.getCodePaiement(), client});
        }
        // Désactiver le bouton "Ajouter" lors de la mise à jour
        btnAdd.setEnabled(true);
    }

    private JPanel createAddPanel() {
        JPanel panel = new JPanel();
        panel.setBorder(new TitledBorder("Ajouter un Client"));

        JPanel row1 = new JPanel();
        row1.add(new JLabel("Prenom : "));
        textPrenom = new JTextField(50);
        row1.add(textPrenom);

        JPanel row2 = new JPanel();
        row2.add(new JLabel("Nom : "));
        textNom = new JTextField(46);
        row2.add(textNom);

        JPanel row3 = new JPanel();
        row3.add(new JLabel("NumeroIdentite : "));
        textNumeroIdentite = new JTextField(46);
        row3.add(textNumeroIdentite);

        JPanel row4 = new JPanel();
        row4.add(new JLabel("Telephone : "));
        textTelephone = new JTextField(46);
        row4.add(textTelephone);

        JPanel row5 = new JPanel();
        row5.add(new JLabel("CodePaiement : "));
        textCodePaiement = new JTextField(46);
        row5.add(textCodePaiement);

        btnAdd = new JButton("Ajouter");
        btnUpdate = new JButton("Modifier");

        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addClient();
            }
        });

        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateClient();
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

    private void addClient() {
        // Récupérer les données depuis les champs de texte
        String prenom = textPrenom.getText();
        String nom = textNom.getText();
        String numeroIdentite = textNumeroIdentite.getText();
        String telephone = textTelephone.getText();
        String codePaiement = textCodePaiement.getText();

        // Créer un nouveau client
        Client newClient = new Client((Long) null, prenom, nom, numeroIdentite, telephone, codePaiement);

        // Utiliser le service pour sauvegarder le nouveau client dans la base de données
        ClientServiceImpl clientService = new ClientServiceImpl();
        clientService.saveClient(newClient);

        // Rafraîchir le tableau des clients
        fetchAndDisplayClientes();

        // Réinitialiser les champs de texte après l'ajout
        textPrenom.setText("");
        textNom.setText("");
        textNumeroIdentite.setText("");
        textTelephone.setText("");
        textCodePaiement.setText("");
    }

    private void updateClient() {
        // Récupérer les données depuis les champs de texte
        String prenom = textPrenom.getText();
        String nom = textNom.getText();
        String numeroIdentite = textNumeroIdentite.getText();
        String telephone = textTelephone.getText();
        String codePaiement = textCodePaiement.getText();

        // Récupérer la ligne sélectionnée dans le tableau
        int selectedRow = tableClientes.getSelectedRow();
        if (selectedRow == -1 || selectedRow >= clientList.size()) {
            // Aucune ligne sélectionnée ou index hors limites, rien à mettre à jour
            return;
        }

        // Récupérer le client correspondant à la ligne sélectionnée
        Client selectedClient = clientList.get(selectedRow);

        // Mettre à jour les données du client
        selectedClient.setPrenom(prenom);
        selectedClient.setNom(nom);
        selectedClient.setNumeroIdentite(numeroIdentite);
        selectedClient.setTelephone(telephone);
        selectedClient.setCodePaiement(codePaiement);

        // Utiliser le service pour mettre à jour le client dans la base de données
        ClientServiceImpl clientService = new ClientServiceImpl();
        clientService.updateClient(selectedClient);

        // Rafraîchir le tableau des clients
        fetchAndDisplayClientes();

        // Réinitialiser les champs de texte après la mise à jour
        textPrenom.setText("");
        textNom.setText("");
        textNumeroIdentite.setText("");
        textTelephone.setText("");
        textCodePaiement.setText("");

        // Activer le bouton "Ajouter" après la mise à jour
        btnAdd.setEnabled(true);
        // Désactiver le bouton "Modifier" après la mise à jour
        btnUpdate.setEnabled(true);
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
    private class ButtonEditor extends JButton implements TableCellRenderer, TableCellEditor {
        private Object clickedValue;

        public ButtonEditor() {
            super("Modifier");
            addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int selectedRow = tableClientes.getSelectedRow();
                    if (selectedRow != -1 && selectedRow < clientList.size()) {
                        Client selectedClient = clientList.get(selectedRow);

                        // Afficher les informations du client dans les champs de texte
                        textPrenom.setText(selectedClient.getPrenom());
                        textNom.setText(selectedClient.getNom());
                        textNumeroIdentite.setText(selectedClient.getNumeroIdentite());
                        textTelephone.setText(selectedClient.getTelephone());
                        textCodePaiement.setText(selectedClient.getCodePaiement());

                        // Désactiver le bouton "Ajouter" pendant la modification
                        btnAdd.setEnabled(false);

                        // Activer le bouton "Modifier" pendant la modification
                        btnUpdate.setEnabled(true);
                    }
                }
            });
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            return this;
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            clickedValue = value;
            return this;
        }

        @Override
        public Object getCellEditorValue() {
            return clickedValue;
        }

		@Override
		public boolean isCellEditable(EventObject anEvent) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean shouldSelectCell(EventObject anEvent) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean stopCellEditing() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public void cancelCellEditing() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void addCellEditorListener(CellEditorListener l) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void removeCellEditorListener(CellEditorListener l) {
			// TODO Auto-generated method stub
			
		}
    }

    public static void main(String[] args) {
        new ViewClient().setVisible(true);
    }
}
