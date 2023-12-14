package entite;

public class Client {

	private Long clientId;
    private String prenom;
    private String nom;
    private String numeroIdentite;
    private String telephone;
    private String codePaiement;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("hello client");

	}

	// Constructeurs, getters et setters

	public Client(long clientId, String prenom, String nom, String numeroIdentite, String telephone, String codePaiement) {
        this.clientId = clientId;
        this.prenom = prenom;
        this.nom = nom;
        this.numeroIdentite = numeroIdentite;
        this.telephone = telephone;
        this.codePaiement = codePaiement;
    }

    public Client(long long1) {
		// TODO Auto-generated constructor stub
	}

	public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getNumeroIdentite() {
        return numeroIdentite;
    }

    public void setNumeroIdentite(String numeroIdentite) {
        this.numeroIdentite = numeroIdentite;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getCodePaiement() {
        return codePaiement;
    }

    public void setCodePaiement(String codePaiement) {
        this.codePaiement = codePaiement;
    }

}
