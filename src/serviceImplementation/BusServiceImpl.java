//package serviceImplementation;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//
//import ConfigDatabase.Databases;
//import entite.Bus;
//import service.ServiceBus;
//public class BusServiceImpl implements ServiceBus {
//
//
//	 public static void main(String[] args) {
////		    System.out.println("hello bus");
//		    }
//
//	@Override
//	public Bus saveBus(Bus bus) {
//		// TODO Auto-generated method stub
//		 String sql = "INSERT INTO Bus (nom, description, etat) VALUES (?, ?, ?)";
//	        try (Connection connection = Databases.getConnection();
//
//	             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
//	        	preparedStatement.setString(1, bus.getNom());
//	            preparedStatement.setString(2, bus.getDescription());
//	            preparedStatement.setString(3, bus.getEtat());
//	            preparedStatement.executeUpdate();
//	        } catch (SQLException e) {
//	            e.printStackTrace();
//	        }
//	        return bus;
//	}
//
////	@Override
//public List<Bus> getAllBuses() {
//        List<Bus> busList = new ArrayList<>();
//        String sql = "SELECT * FROM Bus";
//        try (Connection connection = Databases.getConnection();
//             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
//            ResultSet resultSet = preparedStatement.executeQuery();
//            while (resultSet.next()) {
//                Bus bus = new Bus(resultSet.getLong("busId"),
//                        resultSet.getString("nom"),
//                        resultSet.getString("description"),
//                        resultSet.getString("etat"));
//                busList.add(bus);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return busList;
//    }
//
//	@Override
//	public void updateBus(Bus bus) {
//		// TODO Auto-generated method stub
//		String sql = "UPDATE Bus SET description = ?, etat = ? WHERE busId = ?";
//        try (Connection connection = Databases.getConnection();
//             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
//            preparedStatement.setString(1, bus.getDescription());
//            preparedStatement.setString(2, bus.getEtat());
//            preparedStatement.setLong(3, bus.getBusId());
//            preparedStatement.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//	}
//
//	@Override
//	public void deleteBus(Long busId) {
//		// TODO Auto-generated method stub
//		String sql = "DELETE FROM Bus WHERE busId = ?";
//        try (Connection connection = Databases.getConnection();
//             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
//            preparedStatement.setLong(1, busId);
//            preparedStatement.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//	@Override
//	public Bus getBusById(Long busId) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
////	public List<Bus> getAllBuses() {
////		// TODO Auto-generated method stub
////		return null;
////	}
////
//
//}
package serviceImplementation;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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

import ConfigDatabase.Databases;
import entite.Bus;
import service.ServiceBus;
import serviceImplementation.BusServiceImpl;

public class BusServiceImpl implements ServiceBus {
	 @Override
	    public Bus saveBus(Bus bus) {
	        String sql = "INSERT INTO Bus (nom, description, etat) VALUES (?, ?, ?)";
	        try (Connection connection = Databases.getConnection();
	             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
	        	  preparedStatement.setString(1, bus.getNom());
	            preparedStatement.setString(2, bus.getDescription());
	            preparedStatement.setString(3, bus.getEtat());
	            preparedStatement.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return bus;
	    }

	    @Override
	    public Bus getBusById(Long busId) {
	        String sql = "SELECT * FROM Bus WHERE busId = ?";
	        try (Connection connection = Databases.getConnection();
	             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
	            preparedStatement.setLong(1, busId);
	            ResultSet resultSet = preparedStatement.executeQuery();
	            if (resultSet.next()) {
	                return new Bus(resultSet.getLong("busId"),
	                               resultSet.getString("description"),
	                               resultSet.getString("etat"), sql);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return null;
	    }

		
		 public List<Bus> getAllBuses() {
		        List<Bus> busList = new ArrayList<>();
		        String sql = "SELECT * FROM Bus";
		        try (Connection connection = Databases.getConnection();
		             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
		            ResultSet resultSet = preparedStatement.executeQuery();
		            while (resultSet.next()) {
		                Bus bus = new Bus(resultSet.getLong("busId"),
		                		resultSet.getString("nom"),
		                        resultSet.getString("description"),
		                        resultSet.getString("etat"));
		                busList.add(bus);
		            }
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
		        return busList;
		    }
		 
		 @Override
		 public void updateBus(Bus bus) {
		     String sql = "UPDATE Bus SET nom = ?, description = ?, etat = ? WHERE busId = ?";
		     Connection connection = null; // Déclaration de la connexion à l'extérieur du bloc try
		     
		     try {
		         connection = Databases.getConnection(); // Initialisation de la connexion

		         // Démarrer une transaction
		         connection.setAutoCommit(false);

		         try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
		             preparedStatement.setString(1, bus.getNom());
		             preparedStatement.setString(2, bus.getDescription());
		             preparedStatement.setString(3, bus.getEtat());
		             preparedStatement.setLong(4, bus.getBusId());
		             preparedStatement.executeUpdate();
		         }

		         // Valider la transaction
		         connection.commit();
		     } catch (SQLException e) {
		         // En cas d'erreur, annuler la transaction
		         e.printStackTrace();
		         try {
		             if (connection != null) {
		                 connection.rollback();
		             }
		         } catch (SQLException rollbackException) {
		             rollbackException.printStackTrace();
		         }
		     } finally {
		         // Assurer que la connexion est toujours fermée même en cas d'erreur
		         try {
		             if (connection != null) {
		                 connection.setAutoCommit(true);
		                 connection.close();
		             }
		         } catch (SQLException closeException) {
		             closeException.printStackTrace();
		         }
		     }
		 }


		    @Override
		    public void deleteBus(Long busId) {
		        String sql = "DELETE FROM Bus WHERE busId = ?";
		        try (Connection connection = Databases.getConnection();
		             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
		            preparedStatement.setLong(1, busId);
		            preparedStatement.executeUpdate();
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
		    }
}

