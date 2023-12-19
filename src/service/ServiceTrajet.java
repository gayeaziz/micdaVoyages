package service;

import java.util.List;

import entite.Trajet;

public interface ServiceTrajet {
	Trajet saveTrajet(Trajet trajet);
    Trajet getTrajetById(Long trajetId);
    void updateTrajet(Trajet trajet);
    void deleteTrajet(Long trajetId);
    List<Trajet> getAllTrajetes();

}
