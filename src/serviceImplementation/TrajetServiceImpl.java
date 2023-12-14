package serviceImplementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ConfigDatabase.Databases;
import entite.Trajet;
import service.ServiceTrajet;

public class TrajetServiceImpl  implements ServiceTrajet{
	
	
	@Override
	public Trajet saveTrajet(Trajet trajet) {
		String sql = "INSERT INTO Trajet ( bus, villeDepart, villeArrivee, dateDepart, heureDepart, placesTotales)"
				+ " VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection connection = Databases.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
        	preparedStatement.setNString(1, trajet.getBus());
            preparedStatement.setString(2, trajet.getVilleDepart());
            preparedStatement.setString(3, trajet.getVilleArrivee());
            preparedStatement.setString(4, trajet.getDateDepart());
            preparedStatement.setString(5, trajet.getHeureDepart());
            preparedStatement.setInt(6, trajet.getPlacesTotales());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return trajet;
	}

	@Override
	public Trajet getTrajetById(Long trajetId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateTrajet(Trajet trajet) {
		// TODO Auto-generated method stub
		 String sql = "UPDATE Trajet SET bus = ?, villeDepart = ?, villeArrivee = ? , dateDepart = ? "
		 		+ " , heureDepart = ? , placesTotales = ? WHERE busId = ?";
	     Connection connection = null; // Déclaration de la connexion à l'extérieur du bloc try
	     
	     try {
	         connection = Databases.getConnection(); // Initialisation de la connexion

	         // Démarrer une transaction
	         connection.setAutoCommit(false);

	         try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
	             preparedStatement.setNString(1, trajet.getBus());
	             preparedStatement.setString(2, trajet.getVilleDepart());
	             preparedStatement.setString(3, trajet.getVilleArrivee());
	             preparedStatement.setString(4, trajet.getDateDepart());
	             preparedStatement.setString(5, trajet.getHeureDepart());
	             preparedStatement.setInt(6, trajet.getPlacesTotales());
	             preparedStatement.setLong(7, trajet.getTrajetId());
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
	public void deleteTrajet(Long trajetId) {
		 String sql = "DELETE FROM Trajet WHERE trajetId = ?";
	        try (Connection connection = Databases.getConnection();
	             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
	            preparedStatement.setLong(1, trajetId);
	            preparedStatement.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
		

	@Override
	public List<Trajet> getAllTrajetes() {
	    List<Trajet> trajetList = new ArrayList<>();
	    String sql = "SELECT * FROM Trajet";
	    try (Connection connection = Databases.getConnection();
	         PreparedStatement preparedStatement = connection.prepareStatement(sql);
	         ResultSet resultSet = preparedStatement.executeQuery()) {
	        while (resultSet.next()) {
	            Trajet trajet = new Trajet(
	                    resultSet.getLong("trajetId"),
	                    resultSet.getString("bus"),
	                    resultSet.getString("villeDepart"),
	                    resultSet.getString("villeArrivee"),
	                    resultSet.getString("dateDepart"),
	                    resultSet.getString("heureDepart"),
	                    resultSet.getInt("placesTotales")
	            );
	            trajetList.add(trajet);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return trajetList;
	}

	
	
}
