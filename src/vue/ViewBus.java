package vue;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
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

class ViewBus extends JPanel {
    private JTextField textNom;
    private JTextField textDescription;
    private JTextField textStatus;
    private JTable tableBuses;
    private DefaultTableModel tableModel;
    private List<Bus> busList;

    private JButton btnAdd;
    private JButton btnUpdate;

    public ViewBus() {
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
        btnUpdate = new JButton("Modifier");
        fetchAndDisplayBuses();
    }

    private JPanel createAddPanel() {
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(710, 200));
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        panel.setBorder(new TitledBorder("GESTION DES BUS"));

     // Ajout du titre descriptif
        JLabel titleLabel = new JLabel("GESTION DES BUS");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));

        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.X_AXIS));
        titlePanel.add(Box.createVerticalStrut(10));
        titlePanel.add(titleLabel);
        titlePanel.add(Box.createVerticalStrut(10));

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(titlePanel);
       
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
        panel.add(buttonPanel);

        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        panel.setAlignmentX(CENTER_ALIGNMENT);

        btnAdd.setEnabled(true);

        return panel;
    }

    private void addBus() {
        String nom = textNom.getText();
        String description = textDescription.getText();
        String etat = textStatus.getText();

        Bus newBus = new Bus();
        newBus.setNom(nom);
        newBus.setDescription(description);
        newBus.setEtat(etat);

        BusServiceImpl busService = new BusServiceImpl();
        busService.saveBus(newBus);

        fetchAndDisplayBuses();

        textNom.setText("");
        textDescription.setText("");
        textStatus.setText("");
    }

    private void updateBus() {
        String nom = textNom.getText();
        String description = textDescription.getText();
        String etat = textStatus.getText();

        int selectedRow = tableBuses.getSelectedRow();
        if (selectedRow == -1 || selectedRow >= busList.size()) {
            return;
        }
        

        Bus selectedBus = busList.get(selectedRow);

        selectedBus.setNom(nom);
        selectedBus.setDescription(description);
        selectedBus.setEtat(etat);

        BusServiceImpl busService = new BusServiceImpl();
        busService.updateBus(selectedBus);

        fetchAndDisplayBuses();

        textNom.setText("");
        textDescription.setText("");
        textStatus.setText("");
        btnAdd.setEnabled(true);
    }

    public void fetchAndDisplayBuses() {
        BusServiceImpl busService = new BusServiceImpl();
        busList = busService.getAllBuses();

        tableModel.setRowCount(0);

        for (Bus bus : busList) {
            tableModel.addRow(new Object[]{bus.getNom(), bus.getDescription(), bus.getEtat(), bus});
        }

        btnAdd.setEnabled(true);
    }
    private void deleteBus(int selectedRow) {

    	  Bus selectedBus = busList.get(selectedRow);
    	// Récupérer l'ID
    	  Long busId = selectedBus.getBusId(); 
    	  
    	  BusServiceImpl busService = new BusServiceImpl();
    	  busService.deleteBus(busId);

    	  busList.remove(selectedBus); // Supprimer de la liste locale
    	  
    	  tableModel.removeRow(selectedRow); // Supprimer de l'affichage
    	  
    	  textNom.setText("");
    	  textDescription.setText("");
    	  textStatus.setText("");
    	  
    	}
    
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
            btnDelete.setPreferredSize(new Dimension(85, 15));

            panel.add(btnEdit);
            panel.add(btnDelete);
        }

        @Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            return panel;
        }
    }

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

                        textNom.setText(selectedBus.getNom());
                        textDescription.setText(selectedBus.getDescription());
                        textStatus.setText(selectedBus.getEtat());

                        btnAdd.setEnabled(false);
                        btnUpdate.setEnabled(true);
                    }
                }
            });

            btnDelete.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int selectedRow = tableBuses.getSelectedRow();
                    if (selectedRow != -1 && selectedRow < busList.size()) {
                        deleteBus(selectedRow);
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
        public static void main(String[] args) {
            new ViewBus().setVisible(true);
        }

    }
}
