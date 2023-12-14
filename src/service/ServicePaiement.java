package service;

import java.util.List;

import entite.Paiement;

public interface ServicePaiement {
    void updatePaiement(Paiement paiement);
    void deletePaiement(Long paiementId);
	Paiement savePaiement(Paiement paiement);
	Paiement getPaiementById(Long paiementId);
	Paiement addPaiement(Paiement paiement);
	List<Paiement> getAllPaiements();
}
