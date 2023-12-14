package service;

import java.util.List;

import entite.Client;

public interface ServiceClient {
	Client saveClient(Client client);
	Client getClientById(Long clientId);
	void updateClient(Client client);
	void deleteClient(Long clientId);
	List<Client> getAllClientes();
	void updateclient(Client client);
}
