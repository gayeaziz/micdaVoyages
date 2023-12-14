package entite;

public class Paiement {
    private long paiementId;
    private String codePaiement;
    private String methodePaiement;

    public Paiement() {
        // Constructeur par défaut
    }

    public Paiement(String codePaiement, String methodePaiement, int unusedField) {
        this.codePaiement = codePaiement;
        this.methodePaiement = methodePaiement;
    }


    public long getPaiementId() {
        return paiementId;
    }

    public void setPaiementId(long paiementId) {
        this.paiementId = paiementId;
    }

    public String getCodePaiement() {
        return codePaiement;
    }

    public void setCodePaiement(String codePaiement) {
        this.codePaiement = codePaiement;
    }

    public String getMethodePaiement() {
        return methodePaiement;
    }

    public void setMethodePaiement(String methodePaiement) {
        this.methodePaiement = methodePaiement;
    }

    @Override
    public String toString() {
        return "Paiement [paiementId=" + paiementId + ", codePaiement=" + codePaiement + ", methodePaiement=" + methodePaiement + "]";
    }
}
