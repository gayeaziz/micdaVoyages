package entite;
//
//
//	public class Bus {
//		private Long busId;
//	    private String description;
//	    private String etat;
//	    // Constructeurs, getters et setters
//	    public static void main(String[] args) {
////	    System.out.println("hello bus");
//	    }
//	    public Bus() {
//	        // Vous pouvez initialiser des valeurs par défaut ici si nécessaire
//	    }
//
//	    public Bus(Long busId, String description, String etat) {
//	        this.busId = busId;
//	        this.description = description;
//	        this.etat = etat;
//	    }
//
//
//
//		public Long getBusId() {
//	        return busId;
//	    }
//
//	    public void setBusId(Long busId) {
//	        this.busId = busId;
//	    }
//
//	    public String getDescription() {
//	        return description;
//	    }
//
//	    public void setDescription(String description) {
//	        this.description = description;
//	    }
//
//	    public String getEtat() {
//	        return etat;
//	    }
//
//	    public void setEtat(String etat) {
//	        this.etat = etat;
//	    }
//
//		public void setVisible(boolean b) {
//			// TODO Auto-generated method stub
//
//		}
//		public Object getStatus() {
//			// TODO Auto-generated method stub
//			return null;
//		}
//		public Object getNom() {
//			// TODO Auto-generated method stub
//			return null;
//		}
//
//
//
//}
public class Bus {
    private Long busId; // Assurez-vous d'ajouter un getter et un setter pour l'ID si nécessaire
    private String nom;
    private String description;
    private String etat;

    public Bus(long busId, String nom, String description, String etat) {
        this.busId = busId;
        this.nom = nom;
        this.description = description;
        this.etat = etat;
    }
	public Bus() {
		// TODO Auto-generated constructor stub
	}
	// Accesseurs pour nom, description et etat
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

	public long getBusId() {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
