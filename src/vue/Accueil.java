package vue;
//
//import java.awt.BorderLayout;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//
//import javax.swing.JFrame;
//import javax.swing.JLabel;
//import javax.swing.JMenu;
//import javax.swing.JMenuBar;
//import javax.swing.JMenuItem;
//import javax.swing.JPanel;
//import javax.swing.WindowConstants;
//
//public class Accueil  extends JFrame {
//	    private JMenuBar menuBar;
//	    private JMenu menuBus, menuTrajet,menuClient, menuPaiement,menuReservation;
//	    private JMenuItem busItem1, busItem2, trajetItem1, clientItem1,paiementItem1,reservationItem1;
//	    private JPanel mainPanel;
//	    private ViewBus viewBus;  // Ajout de l'attribut ViewBus
//
//
//	    public Accueil() {
//	    	  mainPanel = new JPanel();
//	    	  viewBus = new ViewBus();
//	         add(mainPanel, BorderLayout.CENTER);
//	    	   // Barre de menus
//	        menuBar = new JMenuBar();
//
//	    	 ///Menu Client
//	    	menuClient = new JMenu("Client");
//	    	clientItem1 = new JMenuItem("Liste Client");
//	    	menuClient.add(clientItem1);
//
//	    	menuPaiement = new JMenu("Paiement");
//	    	paiementItem1 = new JMenuItem("Liste Paiement");
//	    	menuPaiement.add(paiementItem1);
//
//	    	menuReservation = new JMenu("Reservation");
//	    	reservationItem1 = new JMenuItem("Liste Reservation ");
//	    	menuReservation.add(reservationItem1);
//
//	        // Menu Bus et ses sous-menus
//	        menuBus = new JMenu("Bus");
//	        busItem1 = new JMenuItem("Liste bus");
//	        busItem2 = new JMenuItem("Ajout bus");
//	        menuBus.add(busItem1);
//	        menuBus.add(busItem2);
//
//	        // Menu Trajet et ses sous-menus
//	        menuTrajet = new JMenu("Trajet");
//	        trajetItem1 = new JMenuItem("Liste trajets");
////	        trajetItem2 = new JMenuItem("Ajout trajet");
//	        menuTrajet.add(trajetItem1);
////	        menuTrajet.add(trajetItem2);
//
//	        // Ajout à la barre de menus
//	        menuBar.add(menuClient);
//	        menuBar.add(menuBus);
//	        menuBar.add(menuTrajet);
//	        menuBar.add(menuPaiement);
//	        menuBar.add(menuReservation);
//	        setJMenuBar(menuBar);
//
////	         Gestion des clics
////	        busItem1.addActionListener(new ActionListener() {
////	            @Override
////	            public void actionPerformed(ActionEvent e) {
////	                mainPanel.removeAll();
////	                mainPanel.add(new ViewBus());
////	                mainPanel.revalidate();
////	                mainPanel.repaint();
////	             }
////	         });
//
////	        busItem1.addActionListener(new ActionListener() {
////	            public void actionPerformed(ActionEvent evt) {
////	                ViewBus viewBus = new ViewBus();
////
////	                // Redimensionner ViewBus sur la taille de mainPanel
////	                viewBus.setSize(mainPanel.getWidth(), mainPanel.getHeight());
////
////	                // Ajouter ViewBus à mainPanel
////	                mainPanel.removeAll();
////	                mainPanel.add(viewBus.getMainPanel());  // Utiliser getContentPane() pour ajouter le contenu de la JFrame
////
////	                // Redessiner l'interface
////	                revalidate();
////	                repaint();
////	            }
////	        });
//	        busItem1.addActionListener(new ActionListener() {
//	            @Override
//				public void actionPerformed(ActionEvent evt) {
//	                viewBus.fetchAndDisplayBuses(); // Assurez-vous que cette méthode est publique
//	                mainPanel.removeAll();
//	                mainPanel.add(viewBus);
//	                mainPanel.revalidate();
//	                mainPanel.repaint();
//	            }
//	        });
//
//	        clientItem1.addActionListener(new ActionListener() {
//	            @Override
//				public void actionPerformed(ActionEvent evt) {
////	                afficherVue("FormulaireTrajet");
//	            	  ViewClient viewClient = new ViewClient();
//
//		                // Redimensionner ViewBus sur la taille de mainPanel
//	            	  viewClient.setSize(mainPanel.getWidth(), mainPanel.getHeight());
//
//		                // Ajouter ViewBus à mainPanel
//		                mainPanel.removeAll();
//		                mainPanel.add(viewClient);
//
//		                // Redessiner l'interface
//		                revalidate();
//		                repaint();
//
//	            }
//	        });
//
//
//
//        trajetItem1.addActionListener(new ActionListener() {
//	            @Override
//				public void actionPerformed(ActionEvent evt) {
////	                afficherVue("FormulaireTrajet");
//	            	  ViewTrajet viewTrajet = new ViewTrajet();
//
//		                // Redimensionner ViewBus sur la taille de mainPanel
//	            	  viewTrajet.setSize(mainPanel.getWidth(), mainPanel.getHeight());
//
//		                // Ajouter ViewBus à mainPanel
//		                mainPanel.removeAll();
//		                mainPanel.add(viewTrajet);
//
//		                // Redessiner l'interface
//		                revalidate();
//		                repaint();
//
//	            }
//	        });
//
//        paiementItem1.addActionListener(new ActionListener() {
//            @Override
//			public void actionPerformed(ActionEvent evt) {
////                afficherVue("FormulaireTrajet");
//            	  ViewPaiement viewPaiement = new ViewPaiement();
//
//	                // Redimensionner ViewBus sur la taille de mainPanel
//            	  viewPaiement.setSize(mainPanel.getWidth(), mainPanel.getHeight());
//
//	                // Ajouter ViewBus à mainPanel
//	                mainPanel.removeAll();
//	                mainPanel.add(viewPaiement);
//
//	                // Redessiner l'interface
//	                revalidate();
//	                repaint();
//
//            }
//        });
//        reservationItem1.addActionListener(new ActionListener() {
//            @Override
//			public void actionPerformed(ActionEvent evt) {
////                afficherVue("FormulaireTrajet");
//            	  ViewReservation viewReservation = new ViewReservation();
//
//	                // Redimensionner ViewBus sur la taille de mainPanel
//            	  viewReservation.setSize(mainPanel.getWidth(), mainPanel.getHeight());
//
//	                // Ajouter ViewBus à mainPanel
//	                mainPanel.removeAll();
//	                mainPanel.add(viewReservation);
//
//	                // Redessiner l'interface
//	                revalidate();
//	                repaint();
//
//            }
//        });
//        
//	    }
//
//
//
//	    private void afficherVue(String nomVue) {
//	        JPanel vue = new JPanel();
//	        vue.add(new JLabel("Contenu de la vue " + nomVue ));
//	        setContentPane(vue);
//	        validate();
//	    }
//
//	    public static void main(String[] args) {
//	    	Accueil app = new Accueil();
//	        app.setSize(800, 600);
//	        app.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//	        app.setLocationRelativeTo(null);
//	        app.setVisible(true);
//	    }
//
//
//}
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class Accueil extends JFrame {
    private JMenuBar menuBar;
    private JMenu menuBus, menuTrajet, menuClient, menuPaiement, menuReservation;
    private JMenuItem busItem1, trajetItem1, clientItem1, paiementItem1, reservationItem1;
    private JPanel mainPanel;
    private ViewBus viewBus;

    public Accueil() {
        mainPanel = new JPanel();
        viewBus = new ViewBus();
        add(mainPanel, BorderLayout.CENTER);

        // Barre de menus
        menuBar = new JMenuBar();

        // Menu Client
        menuClient = new JMenu("Client");
        clientItem1 = createMenuItem("Liste Client", "");
        menuClient.add(clientItem1);

        // Menu Paiement
        menuPaiement = new JMenu("Paiement");
        paiementItem1 = createMenuItem("Liste Paiement", "");
        menuPaiement.add(paiementItem1);

        // Menu Reservation
        menuReservation = new JMenu("Reservation");
        reservationItem1 = createMenuItem("Liste Reservation", "");
        menuReservation.add(reservationItem1);

        // Menu Bus et ses sous-menus
        menuBus = new JMenu("Bus");
        busItem1 = createMenuItem("Liste bus", "");
//        busItem2 = createMenuItem("Ajout bus", "");
        menuBus.add(busItem1);
        

        // Menu Trajet et ses sous-menus
        menuTrajet = new JMenu("Trajet");
        trajetItem1 = createMenuItem("Liste trajets", "");
        menuTrajet.add(trajetItem1);

        // Ajout à la barre de menus
        menuBar.add(menuClient);
        menuBar.add(menuBus);
        menuBar.add(menuTrajet);
        menuBar.add(menuPaiement);
        menuBar.add(menuReservation);
        setJMenuBar(menuBar);

        // Gestion des clics
        busItem1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
            	ViewBus viewBus = new ViewBus();
            	viewBus.setSize(mainPanel.getWidth(), mainPanel.getHeight());
                mainPanel.removeAll();
                mainPanel.add(viewBus);
                revalidate();
                repaint();
            }
        });

        clientItem1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                ViewClient viewClient = new ViewClient();
                viewClient.setSize(mainPanel.getWidth(), mainPanel.getHeight());
                mainPanel.removeAll();
                mainPanel.add(viewClient);
                revalidate();
                repaint();
            }
        });
        trajetItem1.addActionListener(new ActionListener() {
           @Override
			public void actionPerformed(ActionEvent evt) {
//                afficherVue("FormulaireTrajet");
            	  ViewTrajet viewTrajet = new ViewTrajet();

	                // Redimensionner ViewBus sur la taille de mainPanel
            	  viewTrajet.setSize(mainPanel.getWidth(), mainPanel.getHeight());

	                // Ajouter ViewBus à mainPanel
	                mainPanel.removeAll();
	                mainPanel.add(viewTrajet);

	                // Redessiner l'interface
	                revalidate();
	                repaint();

            }
        });

    paiementItem1.addActionListener(new ActionListener() {
        @Override
		public void actionPerformed(ActionEvent evt) {
//            afficherVue("FormulaireTrajet");
        	  ViewPaiement viewPaiement = new ViewPaiement();

                // Redimensionner ViewBus sur la taille de mainPanel
        	  viewPaiement.setSize(mainPanel.getWidth(), mainPanel.getHeight());

                // Ajouter ViewBus à mainPanel
                mainPanel.removeAll();
                mainPanel.add(viewPaiement);

                // Redessiner l'interface
                revalidate();
                repaint();

        }
    });
    reservationItem1.addActionListener(new ActionListener() {
        @Override
		public void actionPerformed(ActionEvent evt) {
//            afficherVue("FormulaireTrajet");
        	  ViewReservation viewReservation = new ViewReservation();

                // Redimensionner ViewBus sur la taille de mainPanel
        	  viewReservation.setSize(mainPanel.getWidth(), mainPanel.getHeight());

                // Ajouter ViewBus à mainPanel
                mainPanel.removeAll();
                mainPanel.add(viewReservation);

                // Redessiner l'interface
                revalidate();
                repaint();

        }
    });

 // Set orientation to right-to-left
    menuBar.setComponentOrientation(java.awt.ComponentOrientation.LEFT_TO_RIGHT);
    menuBar.setBackground(new Color(173, 216, 230));

        // Style de l'interface
        mainPanel.setBackground(new Color(148, 87, 235)); // Violet

        // Taille de police
        Font menuFont = new Font("Arial", Font.PLAIN, 16);
        setFont(menuFont);
        for (int i = 0; i < menuBar.getMenuCount(); i++) {
            JMenu menu = menuBar.getMenu(i);
            menu.setFont(menuFont);
            for (int j = 0; j < menu.getItemCount(); j++) {
                JMenuItem item = menu.getItem(j);
                if (item != null) {
                    item.setFont(menuFont);
                }
            }
        }

        // Autres paramètres de la fenêtre
        setTitle("Application de Gestion des Reservation");
        setSize(800, 600);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
     // Afficher automatiquement la liste des clients à l'ouverture de l'application
        clientItem1.doClick();
    }

    private JMenuItem createMenuItem(String label, String iconPath) {
        JMenuItem menuItem = new JMenuItem(label);
        if (iconPath != null && !iconPath.isEmpty()) {
            menuItem.setIcon(new ImageIcon(getClass().getResource(iconPath)));
        }
        return menuItem;
    }

    public static void main(String[] args) {
        new Accueil();
    }
}

