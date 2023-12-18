package vue;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EventObject;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
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

import entite.Bus;
import entite.Client;
import service.ServiceClient;
import serviceImplementation.BusServiceImpl;
import serviceImplementation.ClientServiceImpl;

public class ViewClient extends JPanel {

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
//        setVisible(true);
//        setSize(800, 600);
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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


    public void fetchAndDisplayClientes() {
        // Utiliser le service pour récupérer la liste des clients depuis la base de données
        ClientServiceImpl clientService = new ClientServiceImpl();
        clientList = clientService.getAllClientes(); // Mise à jour de la liste clientList

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
        panel.setPreferredSize(new Dimension(730, 360));
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        panel.setBorder(new TitledBorder("GESTION CLIENT"));

        // Ajout du titre descriptif
        JLabel titleLabel = new JLabel("GESTION CLIENT");
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
        Client newClient = new Client();
        newClient.setPrenom(prenom);
        newClient.setNom(nom);
        newClient.setNumeroIdentite(numeroIdentite);
        newClient.setTelephone(telephone);
        newClient.setCodePaiement(codePaiement);

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

    private void deleteClient(int selectedRow) {

//  	  int selectedRow = tableClientes.getSelectedRow();
//
//  	  if(selectedRow == -1) {
//  	    return;
//  	  }

  	  Client selectedclient = clientList.get(selectedRow);
  	// Récupérer l'ID
  	  Long clientId = selectedclient.getClientId(); 
  	  
  	  ClientServiceImpl clientService = new ClientServiceImpl();
  	clientService.deleteClient(clientId);

  	clientList.remove(selectedclient); // Supprimer de la liste locale
  	  
  	  tableModel.removeRow(selectedRow); // Supprimer de l'affichage
  	  
  	  textPrenom.setText("");
      textNom.setText("");
      textNumeroIdentite.setText("");
      textTelephone.setText("");
      textCodePaiement.setText("");
      fetchAndDisplayClientes();
  	}
    
    
    // Classe ButtonRenderer pour rendre correctement les boutons dans la colonne "Actions"
    public class ButtonRenderer implements TableCellRenderer {
        private JPanel panel;
        private JButton btnEdit;
        private JButton btnDelete;

        public ButtonRenderer() {
            panel = new JPanel();
            panel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
            // Reduisez la taille de la police pour les boutons
            Font buttonFont = new Font("Arial", Font.BOLD, 10);

            btnEdit = new JButton("<html><center>Modifier</center></html>");
            btnEdit.setFont(buttonFont);

            btnDelete = new JButton("<html><center>Supprimer</center></html>");
            btnDelete.setFont(buttonFont);
            // Ajuster la taille préférée pour une meilleure mise en page
            btnEdit.setPreferredSize(new Dimension(55, 18));
            btnDelete.setPreferredSize(new Dimension(64, 15));

            panel.add(btnEdit);
            panel.add(btnDelete);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
          
            return panel;
          
            
        }

      
        }

   
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
//            panel.setLayout(new FlowLayout(FlowLayout.CENTER, 1, 0));
//
//            btnEdit = new JButton("Modifier");
//            btnDelete = new JButton("Supprimer");
//
//            btnEdit.addActionListener(new ActionListener() {
//                @Override
//                public void actionPerformed(ActionEvent e) {
//                    int selectedRow = tableClientes.getSelectedRow();
//                    if (selectedRow != -1 && selectedRow < clientList.size()) {
//                        Client selectedClient = clientList.get(selectedRow);
//
//                        textPrenom.setText(selectedClient.getPrenom());
//                        textNom.setText(selectedClient.getNom());
//                        textNumeroIdentite.setText(selectedClient.getNumeroIdentite());
//                        textTelephone.setText(selectedClient.getTelephone());
//                        textCodePaiement.setText(selectedClient.getCodePaiement());

//                        btnAdd.setEnabled(false);
//                        btnUpdate.setEnabled(true);
//                    }
//                }
//            });
//
//            btnDelete.addActionListener(new ActionListener() {
//                @Override
//                public void actionPerformed(ActionEvent e) {
//                	int selectedRow = tableClientes.getSelectedRow();
//                    
//                    if(selectedRow > -1) {
//                      deleteClient(selectedRow); 
//                    }
//                }
//            });
//
//            panel.add(btnEdit);
//            panel.add(btnDelete);
//        }
    private class ButtonEditor extends DefaultCellEditor {
        private JPanel panel;
        private JButton btnEdit;
        private JButton btnDelete;
        private Object clickedValue;

        public ButtonEditor() {
            super(new JTextField());

            panel = new JPanel();
            panel.setLayout(new FlowLayout(FlowLayout.CENTER, 1, 0));

            btnEdit = new JButton("Modifier");
            btnDelete = new JButton("Supprimer");

            btnEdit.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int selectedRow = tableClientes.getSelectedRow();
                    if (selectedRow != -1 && selectedRow < clientList.size()) {
                        Client selectedClient = clientList.get(selectedRow);

                        textPrenom.setText(selectedClient.getPrenom());
                        textNom.setText(selectedClient.getNom());
                        textNumeroIdentite.setText(selectedClient.getNumeroIdentite());
                        textTelephone.setText(selectedClient.getTelephone());
                        textCodePaiement.setText(selectedClient.getCodePaiement());

                        btnAdd.setEnabled(false);
                        btnUpdate.setEnabled(true);
                    }
                }
            });

            btnDelete.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int selectedRow = tableClientes.getSelectedRow();
                    if (selectedRow != -1 && selectedRow < clientList.size()) {
                        deleteClient(selectedRow);
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

       
        public static void main(String[] args) {
            new ViewBus().setVisible(true);
        }

    }
    

