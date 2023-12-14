package serviceImplementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ConfigDatabase.Databases;
import entite.Reservation;
import entite.Trajet;
import entite.Client;
import service.ServiceReservation;

public class ReservationServiceImpl implements ServiceReservation {

    @Override
    public Reservation saveReservation(Reservation reservation) {
        String sql = "INSERT INTO Reservation (trajetId, clientId, numeroSiege, paye) VALUES (?, ?, ?, ?)";
        try (Connection connection = Databases.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setLong(1, reservation.getTrajet().getTrajetId());
            preparedStatement.setLong(2, reservation.getClient().getClientId());
            preparedStatement.setInt(3, reservation.getNumeroSiege());
            preparedStatement.setBoolean(4, reservation.isPaye());
            preparedStatement.executeUpdate();

            // Obtenez l'ID généré pour la réservation nouvellement ajoutée
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                reservation.setReservationId(generatedKeys.getLong(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reservation;
    }

    @Override
    public Reservation getReservationById(Long reservationId) {
        String sql = "SELECT * FROM Reservation WHERE reservationId = ?";
        Reservation reservation = null;
        try (Connection connection = Databases.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, reservationId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Trajet trajet = new Trajet(resultSet.getLong("trajet"));
                Client client = new Client(resultSet.getLong("client"));
                reservation = new Reservation(
                        resultSet.getLong("reservationId"),
                        trajet,
                        client,
                        resultSet.getInt("numeroSiege"),
                        resultSet.getBoolean("paye")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reservation;
    }

    @Override
    public void updateReservation(Reservation reservation) {
        // Implémentation pour mettre à jour une réservation dans la base de données
        // ...
    }

    @Override
    public void deleteReservation(Long reservationId) {
        // Implémentation pour supprimer une réservation de la base de données
        // ...
    }

    @Override
    public List<Reservation> getAllReservations() {
        List<Reservation> reservationList = new ArrayList<>();
        String sql = "SELECT * FROM Reservation";
        try (Connection connection = Databases.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Trajet trajet = new Trajet(resultSet.getLong("trajet"));
                Client client = new Client(resultSet.getLong("client"));
                Reservation reservation = new Reservation(
                        resultSet.getLong("reservationId"),
                        trajet,
                        client,
                        resultSet.getInt("numeroSiege"),
                        resultSet.getBoolean("paye")
                );
                reservationList.add(reservation);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reservationList;
    }

	@Override
	public Reservation addReservation(Reservation reservation) {
		// TODO Auto-generated method stub
		return null;
	}
}
