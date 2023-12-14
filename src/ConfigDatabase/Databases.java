package ConfigDatabase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import entite.Bus;

public class Databases {

	 public static void main(String[] args) {
	        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/micdavoyages", "root", "")) {
	            // Connexion réussie, exécutez une requête de test
	            try (Statement statement = connection.createStatement()) {
	                statement.executeQuery("SELECT 1");
	                System.out.println("La connexion à la base de données est établie avec succès.");
	            }
	        } catch (SQLException e) {
	            System.err.println("Erreur lors de la connexion à la base de données : " + e.getMessage());
	        }
	    }

	 private static final String JDBC_URL = "jdbc:mysql://localhost:3306/micdavoyages";
	    private static final String USERNAME = "root";
	    private static final String PASSWORD = "";

	    public static Connection getConnection() throws SQLException {
	        return DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
	    }

		public static List<Bus> selectAllBuses() {
			// TODO Auto-generated method stub
			return null;
		}
}
