package serviceImplementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ConfigDatabase.Databases;
import entite.Paiement;
import service.ServicePaiement;

public class PaiementServiceImpl implements ServicePaiement {

    @Override
    public Paiement savePaiement(Paiement paiement) {
        // Implémentation pour enregistrer un paiement dans la base de données
        // ...
        return null;
    }

    @Override
    public Paiement getPaiementById(Long paiementId) {
        // Implémentation pour récupérer un paiement par son ID depuis la base de données
        // ...
        return null;
    }
    
    @Override
    public Paiement addPaiement(Paiement paiement) {
        String sql = "INSERT INTO Paiement (codePaiement, methodePaiement) VALUES (?, ?)";
        try (Connection connection = Databases.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, paiement.getCodePaiement());
            preparedStatement.setString(2, paiement.getMethodePaiement());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return paiement;
    }


    @Override
    public void updatePaiement(Paiement paiement) {
        // Implémentation pour mettre à jour un paiement dans la base de données
        // ...
    }

    @Override
    public void deletePaiement(Long paiementId) {
        // Implémentation pour supprimer un paiement de la base de données
        // ...
    }

    @Override
    public List<Paiement> getAllPaiements() {
        List<Paiement> paiementList = new ArrayList<>();
        String sql = "SELECT * FROM Paiement";
        try (Connection connection = Databases.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
            	Paiement paiement = new Paiement(resultSet.getString("codePaiement"),
                        resultSet.getString("methodePaiement"),
                        0);
                paiementList.add(paiement);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return paiementList;
    }
}
