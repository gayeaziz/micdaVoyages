package serviceImplementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ConfigDatabase.Databases;
import entite.Client;
import service.ServiceClient;

public class ClientServiceImpl implements ServiceClient{
	
	@Override
	public Client saveClient(Client client) {
		String sql = "INSERT INTO Client ( prenom, nom, numeroIdentite, telephone, codePaiement)"
				+ " VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = Databases.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
        	preparedStatement.setNString(1, client.getPrenom());
            preparedStatement.setString(2, client.getNom());
            preparedStatement.setString(3, client.getNumeroIdentite());
            preparedStatement.setString(4, client.getTelephone());
            preparedStatement.setString(5, client.getCodePaiement());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return client;
    }
    
	@Override
	public Client getClientById(Long clientId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateclient(Client client) {
	    String sql = "UPDATE Client SET prenom = ?, nom = ?, numeroIdentite = ? , telephone = ? "
	            + " , codePaiement = ? WHERE clientId = ?";
	    Connection connection = null; // Déclaration de la connexion à l'extérieur du bloc try

	    try {
	        connection = Databases.getConnection(); // Initialisation de la connexion

	        // Démarrer une transaction
	        connection.setAutoCommit(false);

	        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
	            preparedStatement.setNString(1, client.getPrenom());
	            preparedStatement.setString(2, client.getNom());
	            preparedStatement.setString(3, client.getNumeroIdentite());
	            preparedStatement.setString(4, client.getTelephone());
	            preparedStatement.setString(5, client.getCodePaiement());
	            preparedStatement.setLong(6, client.getClientId());  // Correction ici, changement du paramètre à la position 6
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
	public void deleteClient(Long clientId) {
		 String sql = "DELETE FROM Client WHERE clientId = ?";
	        try (Connection connection = Databases.getConnection();
	             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
	            preparedStatement.setLong(1, clientId);
	            preparedStatement.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
		

	@Override
	public List<Client> getAllClientes() {
		List<Client> clientList = new ArrayList<>();
        String sql = "SELECT * FROM Client";
        try (Connection connection = Databases.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Client client = new Client(resultSet.getLong("clientId"),
                		resultSet.getString("prenom"),
                        resultSet.getString("nom"),
                        resultSet.getString("numeroIdentite"),
                        resultSet.getString("telephone"),
                        resultSet.getString("codePaiement"));
                  clientList.add(client);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clientList;
	}

	@Override
	public void updateClient(Client client) {
		// TODO Auto-generated method stub
		
	}
	
	
}
