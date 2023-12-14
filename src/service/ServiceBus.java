package service;

import java.util.List;

import entite.Bus;

public interface ServiceBus {
    Bus saveBus(Bus bus);
    Bus getBusById(Long busId);
    void updateBus(Bus bus);
    void deleteBus(Long busId);
    List<Bus> getAllBuses();
}
