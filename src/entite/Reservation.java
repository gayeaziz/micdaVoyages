package entite;

public class Reservation {

	private Long reservationId;
	private String trajetId;
    private Client clientId;
    private String numeroSiege;
    private String paye;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("hello reservation");

	}
	// Constructeurs, getters et setters
    public Reservation() {}

    public Reservation(Long reservationId, String trajetId, Client clientId,
    		String numeroSiege , String paye) {
        this.reservationId = reservationId;
        this.trajetId = trajetId;
        this.clientId = clientId;
        this.numeroSiege = numeroSiege;
        this.paye = paye;
    }

    public Long getReservationId() {
        return reservationId;
    }

    public void setReservationId(Long reservationId) {
        this.reservationId = reservationId;
    }

    public String getTrajet() {
        return trajetId;
    }
   
    public void setTrajetId(String trajetId2) {
        this.trajetId = trajetId2;
    }

    public Client getClient() {
        return clientId;
    }

    public void setClient(Client client) {
        this.clientId = client;
    }


  

    public String isPaye() {
        return paye;
    }

    public void setPaye(String paye2) {
        this.paye = paye2;
    }
	public void setNumeroSiege(String numeroSiege2) {
		// TODO Auto-generated method stub
		
	}
	public void setClient(String clientId2) {
		// TODO Auto-generated method stub
		
	}
	public String getNumeroSiege() {
		// TODO Auto-generated method stub
		return null;
	}
}
