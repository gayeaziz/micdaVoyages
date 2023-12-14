package entite;

public class Trajet {

    private Long trajetId;
    private String bus;
    private String villeDepart;
    private String villeArrivee;
    private String dateDepart;
    private String heureDepart;
    private int placesTotales;

    // Constructeur pour initialiser tous les champs
    public Trajet(Long trajetId, String bus, String villeDepart, String villeArrivee, String dateDepart, String heureDepart, int placesTotales) {
        this.trajetId = trajetId;
        this.bus = bus;
        this.villeDepart = villeDepart;
        this.villeArrivee = villeArrivee;
        this.dateDepart = dateDepart;
        this.heureDepart = heureDepart;
        this.placesTotales = placesTotales;
    }

    // Constructeur avec uniquement l'ID
    public Trajet(Long trajetId) {
        this.trajetId = trajetId;
    }

    // Getters et Setters

    public Trajet(long trajetId2, Bus bus2, String villeDepart2, String villeArrivee2, String dateDepart2,
			String heureDepart2, int placesTotales2) {
		// TODO Auto-generated constructor stub
	}

	public Long getTrajetId() {
        return trajetId;
    }

    public void setTrajetId(Long trajetId) {
        this.trajetId = trajetId;
    }

    public String getBus() {
        return bus;
    }

    public void setBus(String bus) {
        this.bus = bus;
    }

    public String getVilleDepart() {
        return villeDepart;
    }

    public void setVilleDepart(String villeDepart) {
        this.villeDepart = villeDepart;
    }

    public String getVilleArrivee() {
        return villeArrivee;
    }

    public void setVilleArrivee(String villeArrivee) {
        this.villeArrivee = villeArrivee;
    }

    public String getDateDepart() {
        return dateDepart;
    }

    public void setDateDepart(String dateDepart) {
        this.dateDepart = dateDepart;
    }

    public String getHeureDepart() {
        return heureDepart;
    }

    public void setHeureDepart(String heureDepart) {
        this.heureDepart = heureDepart;
    }

    public int getPlacesTotales() {
        return placesTotales;
    }

    public void setPlacesTotales(int placesTotales) {
        this.placesTotales = placesTotales;
    }

    public static void main(String[] args) {
        System.out.println("hello trajet");
    }

	public void setPlacesTotales(String placesTotales2) {
		// TODO Auto-generated method stub
		
	}
}
