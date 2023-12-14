package entite;

public class Reservation {

	private Long reservationId;
    private Trajet trajet;
    private Client client;
    private int numeroSiege;
    private boolean paye;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("hello reservation");

	}
	// Constructeurs, getters et setters

    public Reservation(Long reservationId, Trajet trajet, Client client,
                       int numeroSiege, boolean paye) {
        this.reservationId = reservationId;
        this.trajet = trajet;
        this.client = client;
        this.numeroSiege = numeroSiege;
        this.paye = paye;
    }

    public Long getReservationId() {
        return reservationId;
    }

    public void setReservationId(Long reservationId) {
        this.reservationId = reservationId;
    }

    public Trajet getTrajet() {
        return trajet;
    }

    public void setTrajet(Trajet trajet) {
        this.trajet = trajet;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public int getNumeroSiege() {
        return numeroSiege;
    }

    public void setNumeroSiege(int numeroSiege) {
        this.numeroSiege = numeroSiege;
    }

    public boolean isPaye() {
        return paye;
    }

    public void setPaye(boolean paye) {
        this.paye = paye;
    }
}
