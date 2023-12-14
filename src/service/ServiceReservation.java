package service;

import java.util.List;

import entite.Reservation;

public interface ServiceReservation {
    void updateReservation(Reservation reservation);
    void deleteReservation(Long reservationId);
	Reservation saveReservation(Reservation reservation);
	Reservation getReservationById(Long reservationId);
	Reservation addReservation(Reservation reservation);
	List<Reservation> getAllReservations();
}